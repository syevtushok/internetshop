package mate.academy.internetshop.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.BucketDao;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import org.apache.log4j.Logger;

@Dao
public class BucketDaoJdbcImpl extends AbstractClass<Bucket> implements BucketDao {
    public static final String CREATE_BUCKET_QUERY = "INSERT INTO buckets (user_id) VALUES(?)";
    public static final String GET_BUCKET_BY_USER_ID = "SELECT * FROM buckets"
            + " WHERE user_id = ?;";
    public static final String GET_BUCKET_BY_BUCKET_ID_QUERY = "SELECT * FROM buckets"
            + " WHERE buckets.bucket_id = ?;";
    public static final String GET_ALL_ITEMS_QUERY =
            "SELECT items.item_id, items.name, items.price FROM items"
                    + " INNER JOIN bucket_items ON items.item_id = bucket_items.item_id"
                    + " WHERE bucket_items.bucket_id = ?";
    public static final String GET_TOTAL_PRICE_FOR_BUCKET =
            "SELECT SUM(items.price) AS price FROM items"
                    + " INNER JOIN bucket_items ON items.item_id = bucket_items.item_id"
                    + " WHERE bucket_items.bucket_id = ?";
    public static final String DELETE_BUCKET_BY_ID = "DELETE FROM buckets WHERE bucket_id = ?";
    public static final String ADD_ITEM_TO_BUCKET_QUERY =
            "INSERT INTO bucket_items (bucket_id, item_id) VALUES(?,?)";
    public static final String DELETE_ITEM_FROM_BUCKET_QUERY =
            "DELETE FROM bucket_items WHERE bucket_id = ? AND item_id = ?;";
    public static final String CLEAR_BUCKET_QUERY = "DELETE FROM bucket_items WHERE bucket_id = ?;";
    public static final String GET_ALL_BUCKETS_QUERY = "SELECT * FROM buckets";
    public static final String DELETE_ITEMS_FROM_BUCKET_QUERY =
            "DELETE FROM bucket_items WHERE bucket_id = ?";
    private static Logger logger = Logger.getLogger(BucketDaoJdbcImpl.class);

    public BucketDaoJdbcImpl(Connection connection) {
        super(connection);
    }

    @Override
    public Bucket create(Bucket bucket) {
        try (PreparedStatement statement = connection.prepareStatement(CREATE_BUCKET_QUERY,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setLong(1, bucket.getUserId());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            while (generatedKeys.next()) {
                bucket.setBucketId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            logger.error("Cannot create bucket ", e);
        }
        return addItemsToBucket(bucket);
    }

    @Override
    public Optional<Bucket> get(Long bucketId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(GET_BUCKET_BY_BUCKET_ID_QUERY)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            Bucket bucket = new Bucket();
            while (resultSet.next()) {
                bucket.setUserId(resultSet.getLong("user_id"));
                bucket.setBucketId(resultSet.getLong("bucket_id"));
            }
            bucket.setItems(getAllItems(bucketId));
            getPriceForBucket(bucket);
            return Optional.of(bucket);
        } catch (SQLException e) {
            logger.error("Cannot get bucket with bucket id " + bucketId, e);
        }
        return Optional.empty();
    }

    @Override
    public Bucket update(Bucket bucket) {
        deleteItemsFromBucket(bucket.getBucketId());
        addItemsToBucket(bucket);
        return bucket;
    }

    private void deleteItemsFromBucket(Long bucketId) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE_ITEMS_FROM_BUCKET_QUERY)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot delete items from a bucket with id = " + bucketId, e);
        }
    }

    @Override
    public boolean deleteById(Long bucketId) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BUCKET_BY_ID)) {
            statement.setLong(1, bucketId);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            logger.error("Cannot delete a bucket with id " + bucketId, e);
        }
        return false;
    }

    @Override
    public boolean delete(Bucket bucket) {
        return deleteById(bucket.getBucketId());
    }

    public Bucket addItemsToBucket(Bucket bucket) {
        for (Item item : bucket.getItems()) {
            try (PreparedStatement statement =
                         connection.prepareStatement(ADD_ITEM_TO_BUCKET_QUERY)) {
                statement.setLong(1, bucket.getBucketId());
                statement.setLong(2, item.getItemId());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("Cannot add item to bucket with id " + bucket.getBucketId(), e);
            }
        }
        return bucket;
    }

    @Override
    public Optional<Bucket> getByUserId(Long userId) {
        try (PreparedStatement statement = connection.prepareStatement(GET_BUCKET_BY_USER_ID)) {
            statement.setLong(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return get(resultSet.getLong("bucket_id"));
            }
        } catch (SQLException e) {
            logger.error("Can't get bucket by user's id " + userId, e);
        }
        return Optional.empty();
    }

    @Override
    public void deleteItem(Bucket bucket, Item item) {
        try (PreparedStatement statement =
                     connection.prepareStatement(DELETE_ITEM_FROM_BUCKET_QUERY)) {
            statement.setLong(1, bucket.getBucketId());
            statement.setLong(2, item.getItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot delete item from bucket with id ="
                    + bucket.getBucketId(), e);
        }
    }

    @Override
    public void clear(Bucket bucket) {
        try (PreparedStatement statement = connection.prepareStatement(CLEAR_BUCKET_QUERY)) {
            statement.setLong(1, bucket.getBucketId());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Cannot clear bucket with id = " + bucket.getBucketId(), e);
        }
    }

    @Override
    public List<Item> getAllItems(Long bucketId) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_ITEMS_QUERY)) {
            statement.setLong(1, bucketId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Item item = new Item();
                item.setPrice(resultSet.getBigDecimal("price"));
                item.setName(resultSet.getString("name"));
                item.setItemId(resultSet.getLong("item_id"));
                items.add(item);
            }
        } catch (SQLException e) {
            logger.error("Cannot get all items by bucketId " + bucketId, e);
        }
        return items;
    }

    @Override
    public List<Bucket> getAll() {
        List<Bucket> buckets = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(GET_ALL_BUCKETS_QUERY)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Bucket bucket = get(resultSet.getLong("bucket_id")).orElseThrow();
                buckets.add(bucket);
            }
        } catch (SQLException e) {
            logger.error("Cannot get all buckets");
        }
        return buckets;
    }

    private void getPriceForBucket(Bucket bucket) {
        try (PreparedStatement statement =
                     connection.prepareStatement(GET_TOTAL_PRICE_FOR_BUCKET)) {
            statement.setLong(1, bucket.getBucketId());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bucket.setPrice(resultSet.getBigDecimal("price"));
            }
        } catch (SQLException e) {
            logger.error("Cannot get price for bucket " + bucket.getBucketId(), e);
        }
    }
}
