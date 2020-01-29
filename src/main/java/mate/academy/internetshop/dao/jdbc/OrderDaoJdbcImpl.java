package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoJdbcImpl extends AbstractClass<Order> implements OrderDao {
    public static final String UPDATE_ORDER_QUERY =
            "UPDATE orders SET order_id = ?, WHERE user_id = ?";
    public static final String DELETE_FROM_ORDERS_BY_ID = "DELETE FROM orders WHERE order_id = ?";
    public static final String GET_ALL_ORDERS_QUERY = "SELECT * FROM orders";
    public static final String GET_ALL_ORDERS_FOR_USER = "SELECT * FROM orders WHERE user_id = ?;";
    private static final String CREATE_ORDER_QUERY = "INSERT INTO orders (user_id) VALUES(?)";
    private static final String ADD_ITEMS_TO_DB =
            "INSERT INTO orders_items (order_id, item_id) values(?, ?)";
    private static final String GET_ORDER_BY_ORDER_ID_QUERY = "SELECT * FROM orders"
            + " WHERE orders.order_id = ?;";
    private static final String GET_ALL_ITEMS_QUERY =
            "SELECT items.item_id, items.name, items.price FROM items"
                    + " INNER JOIN orders_items ON items.item_id = orders_items.item_id"
                    + " WHERE orders_items.order_id = ?";

    public OrderDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Order create(Order order) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ORDER_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, order.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                order.setOrderId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot create order " + e);
        }
        addItemsToDb(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long orderId) throws DataProcessingException {
        try (PreparedStatement statement =
                     connection.prepareStatement(GET_ORDER_BY_ORDER_ID_QUERY)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            Order order = new Order();
            while (resultSet.next()) {
                order.setOrderId(resultSet.getLong("order_id"));
                order.setUserId(resultSet.getLong("user_id"));
            }
            order.setItems(getAllItems(orderId));
            return Optional.of(order);
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot get order with order id " + orderId + e);
        }
    }

    @Override
    public Order update(Order order) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ORDER_QUERY)) {
            statement.setLong(1, order.getOrderId());
            statement.setLong(2, order.getUserId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot update order with id "
                    + order.getOrderId() + e);
        }
        return order;
    }

    @Override
    public boolean deleteById(Long orderId) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_FROM_ORDERS_BY_ID)) {
            statement.setLong(1, orderId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete order with id " + orderId + e);
        }
    }

    @Override
    public boolean delete(Order order) throws DataProcessingException {
        return deleteById(order.getOrderId());
    }

    @Override
    public List<Order> getAll() throws DataProcessingException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Order order = get(resultSet.getLong("order_id")).orElseThrow();
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot get all buckets" + e);
        }
        return orders;
    }

    private List<Item> getAllItems(Long orderId) throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ITEMS_QUERY)) {
            statement.setLong(1, orderId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setName(resultSet.getString("name"));
                item.setItemId(resultSet.getLong("item_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot get all items by order id " + orderId + e);
        }
        return items;
    }

    private void addItemsToDb(Order order) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(ADD_ITEMS_TO_DB)) {
            statement.setLong(1, order.getOrderId());
            for (Item item : order.getItems()) {
                statement.setLong(2, item.getItemId());
                statement.execute();
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot add items from order with id "
                    + order.getOrderId() + " to database " + e);
        }
    }

    @Override
    public List<Order> getAllOrdersForUser(Long userId) throws DataProcessingException {
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ORDERS_FOR_USER)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                orders.add(get(resultSet.getLong("order_id")).orElseThrow());
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get orders for user with id = " + userId + e);
        }
        return orders;
    }
}
