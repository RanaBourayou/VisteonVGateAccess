package visteon.gestionacces.RestController;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import visteon.gestionacces.Entities.Role;
import visteon.gestionacces.Entities.User;
import visteon.gestionacces.IServices.IUserServices;
import visteon.gestionacces.Repositories.UserRepository;
import visteon.gestionacces.ServicesImpl.JWTTokenService;
import visteon.gestionacces.ServicesImpl.PasswordResetTokenService;
import visteon.gestionacces.ServicesImpl.UserService;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:4200")
 public class AuthController {

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTTokenService jwtTokenService;
    private final PasswordResetTokenService tokenService;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final IUserServices iuserServices;


    public AuthController(AuthenticationManager authenticationManager,
                          UserService userService,
                          JWTTokenService jwtTokenService, PasswordResetTokenService tokenService, UserRepository userRepository, PasswordEncoder passwordEncoder, IUserServices iuserServices) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.iuserServices = iuserServices;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String password = payload.get("password");

        try {
            // Authenticate credentials
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password)
            );

            // Load user and generate JWT
            User user = userService.loadUserByUsername(email);
            String token = jwtTokenService.generateToken(user);

            // Return token and role
            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "role", user.getRole().name(),
                    "email", user.getEmail(),
                    "firstName", user.getFirstName(),
                    "lastName", user.getLastName()
            ));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid email or password"));
        }
    }

    @PostMapping("/register/requester")
    public ResponseEntity<?> registerRequester(@Valid @RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Collections.singletonMap("error", "Email already in use"));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.REQUESTER);
        User savedUser = userRepository.save(user);

        return ResponseEntity.ok(new AuthResponse(jwtTokenService.generateToken(savedUser)));
    }

    @PostMapping("/register/receptionist")
    public ResponseEntity<?> registerReceptionist(@Valid @RequestBody User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email already in use");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.RECEPTIONIST);
        User savedUser = userRepository.save(user);

        return ResponseEntity.ok(new AuthResponse(jwtTokenService.generateToken(savedUser)));
    }

    // ========== LOGIN ENDPOINTS ========== //

    @PostMapping("/login/requester")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> loginRequester(@RequestBody AuthRequest authRequest) {
        logger.info("Attempting to authenticate requester: {}", authRequest.getEmail());
        return handleLogin(authRequest, Role.REQUESTER);
    }

    @PostMapping("/login/receptionist")
    public ResponseEntity<?> loginReceptionist(@RequestBody AuthRequest authRequest) {
        logger.info("Attempting to authenticate receptionist: {}", authRequest.getEmail());
        return handleLogin(authRequest, Role.RECEPTIONIST);
    }

    @PostMapping("/login/admin")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> loginAdmin(@RequestBody AuthRequest authRequest) {
        logger.info("Attempting to authenticate admin: {}", authRequest.getEmail());
        return handleLogin(authRequest, Role.ADMIN);
    }

    private ResponseEntity<?> handleLogin(AuthRequest authRequest, Role requiredRole) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authRequest.getEmail(),
                            authRequest.getPassword()
                    )
            );

            User user = userService.loadUserByUsername(authRequest.getEmail());

            // Role validation
            if (user.getRole() != requiredRole) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Collections.singletonMap("error",
                                "Access restricted to " + requiredRole + " role"));
            }


            return ResponseEntity.ok(new AuthResponse(jwtTokenService.generateToken(user)));

        } catch (AuthenticationException e) {
            logger.error("Authentication failed: {}", authRequest.getEmail(), e);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("error", "Invalid credentials"));
        }
    }

    // ========== PASSWORD MANAGEMENT ========== //

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestParam String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = UUID.randomUUID().toString();
        tokenService.createPasswordResetTokenForUser(user, token);
        //emailService.sendPasswordResetEmail(user, token);

        return ResponseEntity.ok("Password reset link sent to your email");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(
            @RequestParam String token,
            @RequestParam String newPassword) {

        User user = tokenService.validatePasswordResetToken(token);
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok("Password reset successfully");
    }

    @PostMapping("/reset-password-with-old")
    public ResponseEntity<?> resetPasswordWithOld(
            @RequestParam String email,
            @RequestParam String oldPassword,
            @RequestParam String newPassword) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Old password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        return ResponseEntity.ok("Password reset successfully");
    }


    @Getter
    public static class AuthRequest {
        private String email;
        private String password;
    }

    @Data
    @Builder
    public static class AuthResponse {
        private String token;

        public AuthResponse(String token) {
            this.token = token;
        }
    }
}