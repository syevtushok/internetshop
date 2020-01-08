package mate.academy.internetshop.service.imp;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    private static OrderDao orderDao;
    @Inject
    private static UserDao userDao;

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
    public boolean delete(Order item) {
        return orderDao.delete(item);
    }

    @Override
    public boolean deleteById(Long id) {
        return orderDao.deleteById(id);
    }

    @Override
    public Order completeOrder(List<Item> items, User user) {
        Order order = new Order(items, user.getId());
        return create(order);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return getAll().stream()
                .filter(order -> order.getUserId().equals(user.getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
