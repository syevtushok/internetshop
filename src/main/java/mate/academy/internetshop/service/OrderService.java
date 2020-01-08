package mate.academy.internetshop.service;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface OrderService {
    Order create(Order item);

    Optional<Order> get(Long id);

    Order update(Order item);

    void delete(Long id);

    void delete(Order item);

    Order completeOrder(List<Item> items, User user);

    List<Order> getUserOrders(User user);

    List<Order> getAllOrders();
}
