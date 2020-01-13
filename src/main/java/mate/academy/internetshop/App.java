package mate.academy.internetshop;

import java.math.BigDecimal;

import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Injector;
import mate.academy.internetshop.model.Bucket;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.BucketService;
import mate.academy.internetshop.service.ItemService;
import mate.academy.internetshop.service.OrderService;
import mate.academy.internetshop.service.UserService;
import org.apache.log4j.Logger;

public class App {

    static final Logger logger = Logger.getLogger(App.class);
    @Inject
    static ItemService itemService;
    @Inject
    static BucketService bucketService;
    @Inject
    static UserService userService;
    @Inject
    static OrderService orderService;

    static {
        try {
            Injector.injectDependency();
        } catch (IllegalAccessException e) {
            logger.error(e);
        }
    }

    public static void main(String[] args) {
        logger.info("=============================");
        bucketTests();
        logger.info("=============================");
        itemTest();
        logger.info("=============================");
        userTest();
        logger.info("=============================");
        orderTest();
        logger.info("=============================");
    }

    private static void orderTest() {
        Order order1 = new Order();
        Order order2 = new Order();
        logger.info("Create order1 " + orderService.create(order1));
        logger.info("Create order2 " + orderService.create(order2));
        logger.info("Get order1 " + orderService.get(order1.getOrderId()));
        logger.info("Get order2 " + orderService.get(order2.getOrderId()));
        User user = new User();
        user.setName("Cool man");
        userService.create(user);
        order1.setUserId(user.getId());
        order2.setUserId(user.getId());
        logger.info("Update order1: " + orderService.update(order1));
        logger.info("All user's orders:" + orderService.getUserOrders(user));
        logger.info("All orders: " + orderService.getAll());
        orderService.deleteById(order1.getOrderId());
        orderService.delete(order2);
        logger.info("All orders: " + orderService.getAll());
    }

    private static void userTest() {
        User user1 = new User();
        User user2 = new User();
        user1.setLogin("moderator");
        logger.info("Create user1 " + userService.create(user1));
        logger.info("Create user2 " + userService.create(user2));
        logger.info("Get user: " + userService.get(user1.getId()));
        logger.info("Get user: " + userService.get(user2.getId()));
        logger.info("All users:" + userService.getAll());
        user2.setLogin("moderator's friend");
        logger.info("Update user2 " + userService.update(user2));
        logger.info("All users:" + userService.getAll());
        userService.delete(user1);
        userService.deleteById(user2.getId());
        logger.info("All users:" + userService.getAll());
    }

    private static void itemTest() {
        Item item = new Item();
        Item item1 = new Item("Polish-1231", BigDecimal.valueOf(99.9));
        logger.info("Create Item: " + itemService.create(item));
        logger.info("Create Item: " + itemService.create(item1));
        logger.info("Get item: " + itemService.get(item.getItemId()));
        logger.info("Get item: " + itemService.get(item1.getItemId()));
        item.setName("dalwekd");
        logger.info("Update item:" + itemService.update(item));
        logger.info("All items " + itemService.getAll());
        logger.info(itemService.deleteById(item.getItemId()));
        logger.info(itemService.delete(item1));
        logger.info(itemService.getAll());
    }

    private static void bucketTests() {
        Bucket bucket = new Bucket(1L);
        Item item1 = new Item("Polish-1231", BigDecimal.valueOf(99.9));
        Item item2 = new Item("ASK-434", BigDecimal.valueOf(2121));
        itemService.create(item1);
        itemService.create(item2);

        Bucket bucket1 = new Bucket(1L);
        logger.info("Create Bucket: " + bucketService.create(bucket));
        logger.info("Create Bucket: " + bucketService.create(bucket1));
        logger.info("Get bucket with id 0:" + bucketService.get(0L));
        logger.info("Get bucket with id 1:" + bucketService.get(1L));
        bucketService.addItem(bucket, item1);
        bucketService.addItem(bucket, item2);

        User user = new User();
        user.setLogin("admin");
        bucket1.setUserId(user.getId());
        logger.info("Update bucket with id 1: " + bucketService.update(bucket1));
        logger.info("Get all items " + bucketService.getAllItems(bucket.getBucketId()));
        logger.info("Get size:" + Storage.buckets.size());
        logger.info("Delete bucket with id 1 " + bucketService.deleteById(1L));
        logger.info("Get size:" + Storage.buckets.size());
        logger.info("Get all items bucket with id 0"
                + bucketService.getAllItems(bucket.getBucketId()));
        bucketService.clear(bucket);
        logger.info("Get all items bucket with id 0"
                + bucketService.getAllItems(bucket.getBucketId()));
    }
}
