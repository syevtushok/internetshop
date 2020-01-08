package mate.academy.internetshop.dao;

import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.model.Order;

public interface OrderDao {
    Order create(Order item);

    Optional<Order> get(Long id);

    Order update(Order item);

    boolean delete(Long id);

    boolean delete(Order item);

    List<Order> getAllOrders();

}
