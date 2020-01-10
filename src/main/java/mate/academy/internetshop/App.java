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

public class App {

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
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println("=============================");
        bucketTests();
        System.out.println("=============================");
        itemTest();
        System.out.println("=============================");
        userTest();
        System.out.println("=============================");
        orderTest();
        System.out.println("=============================");

    }

    private static void orderTest() {
        Order order1 = new Order();
        Order order2 = new Order();
        System.out.println("Create order1 " + orderService.create(order1));
        System.out.println("Create order2 " + orderService.create(order2));
        System.out.println("Get order1 " + orderService.get(order1.getOrderId()));
        System.out.println("Get order2 " + orderService.get(order2.getOrderId()));
        User user = new User();
        user.setName("Cool man");
        userService.create(user);
        order1.setUserId(user.getId());
        order2.setUserId(user.getId());
        System.out.println("Update order1: " + orderService.update(order1));
        System.out.println("All user's orders:" + orderService.getUserOrders(user));
        System.out.println("All orders: " + orderService.getAll());
        orderService.deleteById(order1.getOrderId());
        orderService.delete(order2);
        System.out.println("All orders: " + orderService.getAll());
    }

    private static void userTest() {
        User user1 = new User();
        User user2 = new User();
        user1.setLogin("moderator");
        System.out.println("Create user1 " + userService.create(user1));
        System.out.println("Create user2 " + userService.create(user2));
        System.out.println("Get user: " + userService.get(user1.getId()));
        System.out.println("Get user: " + userService.get(user2.getId()));
        System.out.println("All users:" + userService.getAll());
        user2.setLogin("moderator's friend");
        System.out.println("Update user2 " + userService.update(user2));
        System.out.println("All users:" + userService.getAll());
        userService.delete(user1);
        userService.deleteById(user2.getId());
        System.out.println("All users:" + userService.getAll());
    }

    private static void itemTest() {
        Item item = new Item();
        Item item1 = new Item("Polish-1231", BigDecimal.valueOf(99.9));
        System.out.println("Create Item: " + itemService.create(item));
        System.out.println("Create Item: " + itemService.create(item1));
        System.out.println("Get item: " + itemService.get(item.getItemId()));
        System.out.println("Get item: " + itemService.get(item1.getItemId()));
        item.setName("dalwekd");
        System.out.println("Update item:" + itemService.update(item));
        System.out.println("All items " + itemService.getAll());
        System.out.println(itemService.deleteById(item.getItemId()));
        System.out.println(itemService.delete(item1));
        System.out.println(itemService.getAll());
    }

    private static void bucketTests() {
        Bucket bucket = new Bucket(1L);
        Item item1 = new Item("Polish-1231", BigDecimal.valueOf(99.9));
        Item item2 = new Item("ASK-434", BigDecimal.valueOf(2121));
        itemService.create(item1);
        itemService.create(item2);

        Bucket bucket1 = new Bucket(1L);
        System.out.println("Create Bucket: " + bucketService.create(bucket));
        System.out.println("Create Bucket: " + bucketService.create(bucket1));
        System.out.println("Get bucket with id 0:" + bucketService.get(0L));
        System.out.println("Get bucket with id 1:" + bucketService.get(1L));
        bucketService.addItem(bucket, java.util.Optional.of(item1));
        bucketService.addItem(bucket, java.util.Optional.of(item2));

        User user = new User();
        user.setLogin("admin");
        bucket1.setUserId(user.getId());
        System.out.println("Update bucket with id 1: " + bucketService.update(bucket1));
        System.out.println("Get all items " + bucketService.getAllItems(bucket.getBucketId()));
        System.out.println("Get size:" + Storage.buckets.size());
        System.out.println("Delete bucket with id 1 " + bucketService.deleteById(1L));
        System.out.println("Get size:" + Storage.buckets.size());

        System.out.println("Get all items bucket with id 0"
                + bucketService.getAllItems(bucket.getBucketId()));
        bucketService.clear(bucket);
        System.out.println("Get all items bucket with id 0"
                + bucketService.getAllItems(bucket.getBucketId()));
    }
}
