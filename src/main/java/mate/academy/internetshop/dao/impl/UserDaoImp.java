package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoImp implements UserDao {
    static Long generatedUserId = 0L;

    @Override
    public User create(User user) {
        user.setUserId(generatedUserId++);
        Storage.users.add(user);
        return user;
    }

    @Override
    public Optional<User> get(Long id) {
        return Optional.ofNullable(Storage.users.stream()
                .filter(x -> x.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find user with id: " + id)));
    }

    @Override
    public User update(User user) {
        User updatedUser = get(user.getId()).get();
        updatedUser.setName(user.getName());
        updatedUser.setSurname(user.getSurname());
        updatedUser.setLogin(user.getLogin());
        updatedUser.setPassword(user.getPassword());
        return null;
    }

    @Override
    public boolean delete(Long id) {
        Optional<User> deletedUser = Optional.ofNullable(Storage.users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Can't find user with id " + id)));
        return Storage.users.remove(deletedUser.get());
    }

    @Override
    public boolean delete(User user) {
        return Storage.users.remove(user);
    }

    @Override
    public List<User> getAllUsers() {
        return Storage.users;
    }
}
