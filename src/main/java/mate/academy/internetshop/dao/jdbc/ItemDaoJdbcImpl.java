package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;

@Dao
public class ItemDaoJdbcImpl extends AbstractClass<Item> implements ItemDao {
    private static final String CREATE_ITEM_QUERY = "INSERT INTO items (name, price) VALUES(?, ?)";
    private static final String GET_ITEM_QUERY = "SELECT * FROM items where item_id = ?;";
    private static final String UPDATE_ITEM_QUERY =
            "UPDATE items SET name = '?', price = ?, WHERE item_id = ?;";
    private static final String DELETE_ITEM_BY_ID = "DELETE FROM items WHERE item_id = ?";
    private static final String GET_ALL_ITEMS_QUERY = "SELECT * FROM items";

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_ITEM_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setBigDecimal(2, item.getPrice());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            while (resultSet.next()) {
                item.setItemId(resultSet.getLong(1));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot create item with name "
                    + item.getName() + e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(GET_ITEM_QUERY)) {
            statement.setLong(1, itemId);
            ResultSet resultSet = statement.executeQuery();
            Item item = new Item();
            while (resultSet.next()) {
                item = getItem(resultSet);
            }
            return Optional.of(item);
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get item by id " + itemId + e);
        }
    }

    @Override
    public Item update(Item item) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_ITEM_QUERY)) {
            statement.setString(1, item.getName());
            statement.setBigDecimal(2, item.getPrice());
            statement.setLong(3, item.getItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot update item " + item + e);
        }
        return item;
    }

    @Override
    public boolean deleteById(Long itemId) throws DataProcessingException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_ITEM_BY_ID)) {
            statement.setLong(1, itemId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new DataProcessingException("Cannot delete item with id " + itemId + e);
        }
    }

    @Override
    public boolean delete(Item item) throws DataProcessingException {
        return deleteById(item.getItemId());
    }

    @Override
    public List<Item> getAll() throws DataProcessingException {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ITEMS_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = getItem(resultSet);
                items.add(item);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Can't get all items " + e);
        }
        return items;
    }

    private Item getItem(ResultSet resultSet) throws SQLException {
        Item item = new Item();
        item.setItemId(resultSet.getLong("item_id"));
        item.setName(resultSet.getString("name"));
        item.setPrice(resultSet.getBigDecimal("price"));
        return item;
    }
}
