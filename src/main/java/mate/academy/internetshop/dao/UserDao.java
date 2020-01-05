package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.User;

public interface UserDao {
    User create(User item);

    Optional<User> get(Long id);

    User update(User item);

    boolean delete(Long id);

    boolean delete(User user);

    List<User> getAllUsers();
}

