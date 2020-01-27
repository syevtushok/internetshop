package mate.academy.internetshop.service.imp;

import java.math.BigDecimal;
import java.util.List;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.exceptions.DataProcessingException;
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
    public Order completeOrder(List<Item> items, User user) throws DataProcessingException {
        Order order = new Order(items, user.getId());
        BigDecimal totalPrice = new BigDecimal(0);
        for (Item item : order.getItems()) {
            totalPrice = totalPrice.add(item.getPrice());
        }
        order.setSumOfMoney(totalPrice);
        return create(order);
    }

    @Override
    public Order create(Order item) throws DataProcessingException {
        return orderDao.create(item);
    }

    @Override
    public Order get(Long id) throws DataProcessingException {
        return orderDao.get(id).orElseThrow();
    }

    @Override
    public Order update(Order item) throws DataProcessingException {
        return orderDao.update(item);
    }

    @Override
    public boolean delete(Order item) throws DataProcessingException {
        return orderDao.delete(item);
    }

    @Override
    public boolean deleteById(Long id) throws DataProcessingException {
        return orderDao.deleteById(id);
    }

    @Override
    public List<Order> getUserOrders(User user) throws DataProcessingException {
        return orderDao.getAllOrdersForUser(user.getId());
    }

    @Override
    public List<Order> getAll() throws DataProcessingException {
        return orderDao.getAll();
    }
}
