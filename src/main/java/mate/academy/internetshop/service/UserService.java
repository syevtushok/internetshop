package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.User;

public interface UserService {
    User create(User user);

    Optional<User> get(Long id);

    User update(User item);

    void delete(Long id);

    void delete(User user);

    List<User> getAllUsers();
}
