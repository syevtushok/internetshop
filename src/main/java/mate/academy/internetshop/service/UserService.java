package mate.academy.internetshop.service;

import java.util.Optional;

import mate.academy.internetshop.exceptions.AuthenticationException;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.model.User;

public interface UserService extends GenericService<User, Long> {
    boolean delete(User user) throws DataProcessingException;

    boolean deleteById(Long userId) throws DataProcessingException;

    User login(String login, String password) throws AuthenticationException,
            DataProcessingException;

    Optional<User> getByToken(String token) throws DataProcessingException;
}
