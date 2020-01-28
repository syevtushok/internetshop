package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Role;
import mate.academy.internetshop.model.User;

@Dao
public class UserDaoJdbcImpl extends AbstractClass<User> implements UserDao {
    public static final int DEFAULT_ROLE_USER = 2;
    public static final String FIND_BY_LOGIN_QUERY =
            "SELECT * FROM users INNER JOIN test.roles_users ON"
                    + " test.users.id = test.roles_users.user_id INNER JOIN test.roles ON"
                    + " test.roles_users.role_id = test.roles.roles_id WHERE login = ?;";
    public static final String FIND_BY_TOKEN_QUERY = "SELECT * FROM users INNER JOIN roles_users ON"
            + " users.id = roles_users.user_id INNER JOIN roles ON"
            + " roles_users.role_id = roles.roles_id WHERE token = ?;";
    public static final String CREATE_USER_QUERY =
            "INSERT INTO users (" + "name, surname, login, password, token, salt) "
                    + "VALUES(?, ?, ?, ?, ?, ?);";
    public static final String SET_ROLE_FOR_USER_QUERY =
            "INSERT INTO roles_users (role_id, user_id) VALUES(?, ?)";
    public static final String GET_USER_QUERY = "SELECT * FROM users where id = ?;";
    public static final String UPDATE_USER_QUERY =
            "UPDATE users SET name = ?, surname = ?, login = ?"
                    + ", password = ?, token = ?, salt = ? WHERE id = ?;";
    public static final String DELETE_USER_BY_ID_QUERY = "DELETE FROM users WHERE id = ?";
    public static final String GET_ALL_USERS_QUERY = "SELECT * FROM users;";

    public UserDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Optional<User> findByLogin(String login) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_LOGIN_QUERY)) {
            return getUser(login, statement);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user by login " + login + e);
        }
    }

    @Override
    public Optional<User> findByToken(String token) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(FIND_BY_TOKEN_QUERY)) {
            return getUser(token, statement);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user by token " + token + e);
        }
    }

    private Optional<User> getUser(String identification,
                                   PreparedStatement statement)
            throws SQLException {
        Set<Role> roles = new HashSet<>();
        statement.setString(1, identification);
        ResultSet resultSet = statement.executeQuery();
        User user = new User();
        while (resultSet.next()) {
            Role role = Role.of(resultSet.getString("role"));
            roles.add(role);
            user = getUser(resultSet);
        }
        user.setRoles(roles);
        return Optional.of(user);
    }

    private User getUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setSurname(resultSet.getString("surname"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setToken(resultSet.getString("token"));
        user.setSalt(resultSet.getBytes("salt"));
        return user;
    }

    @Override
    public User create(User user) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_USER_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.setBytes(6, user.getSalt());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
            setRoleForUser(user.getId());
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot create user " + e);
        }
        return user;
    }

    @Override
    public Optional<User> get(Long userId) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(GET_USER_QUERY)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            User user = new User();
            while (resultSet.next()) {
                user = getUser(resultSet);
            }
            return Optional.of(user);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get user by id " + userId + e);
        }
    }

    @Override
    public User update(User user) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_QUERY)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getToken());
            statement.setLong(6, user.getId());
            statement.setBytes(7, user.getSalt());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot update user with id" + user.getId() + e);
        }
        return user;
    }

    @Override
    public boolean deleteById(Long userId) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_USER_BY_ID_QUERY)) {
            statement.setLong(1, userId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete user with id " + userId + e);
        }
    }

    @Override
    public boolean delete(User user) throws DataProcessingException {
        return deleteById(user.getId());
    }

    @Override
    public List<User> getAll() throws DataProcessingException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_USERS_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            User user;
            while (resultSet.next()) {
                user = getUser(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all users " + e);
        }
        return users;
    }

    private void setRoleForUser(Long userId) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(SET_ROLE_FOR_USER_QUERY)) {
            statement.setInt(1, DEFAULT_ROLE_USER);
            statement.setLong(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Can't insert role for user " + userId + e);
        }
    }
}
