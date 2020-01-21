package mate.academy.internetshop.dao.jdbc;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.ItemDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class ItemDaoJdbcImpl extends AbstractClass<Item> implements ItemDao {
    private static final String DB_NAME = "test";
    private static Logger logger = Logger.getLogger(ItemDaoJdbcImpl.DB_NAME);

    public ItemDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Item create(Item item) {
        String name = item.getName();
        BigDecimal price = item.getPrice();
        String query = String.format("INSERT INTO %s.items (name, price) VALUES('%s', %f)",
                DB_NAME, name, price);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Cannot create item" + e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) {
        String query = String.format("SELECT * FROM %s.items where item_id = %d;", DB_NAME, itemId);
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Item item = getItem(rs);
            return Optional.of(item);

        } catch (SQLException e) {
            logger.error("Can't get item by id " + itemId + e);
        }

        return Optional.empty();
    }

    @Override
    public Item update(Item item) {
        String query = String.format(
                "UPDATE %s.items SET name = '%s', price = %f, WHERE item_id = %d;",
                DB_NAME, item.getName(), item.getPrice(), item.getItemId());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Cannot update item" + e);
        }
        return item;
    }

    @Override
    public boolean deleteById(Long itemId) {
        String query = String.format("DELETE FROM %s.items WHERE item_id = %d",
                DB_NAME, itemId);
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            logger.error("Cannot delete item" + e);
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        String query = String.format("DELETE FROM %s.items WHERE item_id = %d",
                DB_NAME, item.getItemId());
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            logger.error("Cannot delete item" + e);
        }
        return false;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String query = String.format("SELECT * FROM %s.items;", DB_NAME);
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Item item = getItem(rs);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get all items " + e);
        }
        return items;
    }

    private Item getItem(ResultSet rs) throws SQLException {
        long id = rs.getLong("item_id");
        String name = rs.getString("name");
        BigDecimal price = rs.getBigDecimal("price");
        Item item = new Item();
        item.setItemId(id);
        item.setName(name);
        item.setPrice(price);
        return item;
    }
}
