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
        String query = "INSERT INTO " + DB_NAME + ".items (name, price) "
                + "VALUES('" + name + "', " + price + ")";
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch (SQLException e) {
            logger.error("Cannot create item\n" + e);
        }
        return item;
    }

    @Override
    public Optional<Item> get(Long itemId) {
        String query = "SELECT * FROM " + DB_NAME + ".items where item_id = " + itemId + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            Item item = getItem(rs);
            return Optional.of(item);

        } catch (SQLException e) {
            logger.error("Can't get item by id " + itemId + "\n" + e);
        }

        return Optional.empty();
    }

    @Override
    public Item update(Item item) {
        String query = "UPDATE " + DB_NAME + ".items "
                + "SET name = '" + item.getName()
                + "', price = " + item.getPrice()
                + " WHERE item_id = " + item.getItemId() + ";";
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("Cannot update item\n" + e);
        }
        return item;
    }

    @Override
    public boolean deleteById(Long itemId) {
        String query = "DELETE FROM " + DB_NAME + ".items "
                + "WHERE item_id = " + itemId;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            logger.error("Cannot delete item\n" + e);
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        String query = "DELETE FROM " + DB_NAME + ".items "
                + "WHERE item_id = " + item.getItemId();
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            logger.error("Cannot delete item\n" + e);
        }
        return false;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        String query = "SELECT * FROM " + DB_NAME + ".items" + ";";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Item item = getItem(rs);
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Can't get all items " + "\n" + e);
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
