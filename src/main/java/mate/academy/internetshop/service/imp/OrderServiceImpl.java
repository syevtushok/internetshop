package mate.academy.internetshop.service.imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.dao.UserDao;
import mate.academy.internetshop.lib.Inject;
import mate.academy.internetshop.lib.Service;
import mate.academy.internetshop.model.Item;
import mate.academy.internetshop.model.Order;
import mate.academy.internetshop.model.User;
import mate.academy.internetshop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
    @Inject
    static OrderDao orderDao;
    @Inject
    static UserDao userDao;

    @Override
    public Order create(Order item) {
        return orderDao.create(item);
    }

    @Override
    public Optional<Order> get(Long id) {
        return orderDao.get(id);
    }

    @Override
    public Order update(Order item) {
        return orderDao.update(item);
    }

    @Override
    public void delete(Long id) {
        orderDao.delete(id);
    }

    @Override
    public void delete(Order item) {
        orderDao.delete(item);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order order = new Order(items, user.getId());
        return create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        List<Order> orderList = new ArrayList<>();
        for (Order order : orderDao.getAll()) {
            if (order.getUserId().equals(user.getId())) {
                orderList.add(order);
            }
        }
        return orderList;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
}
