package visteon.gestionacces.ServicesImpl;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import visteon.gestionacces.Entities.Role;
import visteon.gestionacces.Entities.User;
import visteon.gestionacces.Repositories.UserRepository;
import visteon.gestionacces.IServices.IUserServices;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserServices {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findUsersByRole(Role role) {
        return userRepository.findByRole(role);
    }



    @Override
    public User updateUser(Long id, User updatedUser) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User with ID " + id + " not found");
        }
        return userRepository.save(updatedUser);
    }




}
