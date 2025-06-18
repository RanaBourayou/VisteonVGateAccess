package visteon.gestionacces.IServices;


import visteon.gestionacces.Entities.Role;
import visteon.gestionacces.Entities.User;

import java.util.List;
import java.util.Optional;

public interface IUserServices {
    List<User> findAllUsers();
    Optional<User> findUserById(Long id);
    List<User> findUsersByRole(Role role);
    User updateUser(Long id, User user);
}
