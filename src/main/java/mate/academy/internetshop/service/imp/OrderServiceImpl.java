package mate.academy.internetshop.service.imp;

import java.util.List;

import mate.academy.internetshop.dao.OrderDao;
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

    @Override
    public Order create(Order item) {
        return orderDao.create(item);
    }

    @Override
    public Order get(Long id) {
        return orderDao.get(id).orElseThrow();
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
        return orderDao.completeOrder(items, user);
    }

    @Override
    public List<Order> getUserOrders(User user) {
        return orderDao.getAllOrdersForUser(user.getId());
    }

    @Override
    public List<Order> getAll() {
        return orderDao.getAll();
    }
}
