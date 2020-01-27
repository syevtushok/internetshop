package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getAllOrdersForUser(Long user);

    Order completeOrder(List<Item> items, User user);
}
