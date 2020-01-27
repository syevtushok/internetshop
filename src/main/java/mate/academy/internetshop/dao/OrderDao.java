package mate.academy.internetshop.dao;

import java.util.List;

import mate.academy.internetshop.model.Order;

public interface OrderDao extends GenericDao<Order, Long> {
    List<Order> getAllOrdersForUser(Long user);
}
