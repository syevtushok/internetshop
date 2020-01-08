package mate.academy.internetshop.dao.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import mate.academy.internetshop.dao.OrderDao;
import mate.academy.internetshop.db.Storage;
import mate.academy.internetshop.lib.Dao;
import mate.academy.internetshop.model.Order;

@Dao
public class OrderDaoImp implements OrderDao {
    private static Long generatedOrderId = 0L;

    @Override
    public Order create(Order order) {
        order.setOrderId(generatedOrderId++);
        Storage.orders.add(order);
        return order;
    }

    @Override
    public Optional<Order> get(Long id) {
        return Optional.ofNullable(Storage.orders
                .stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Can't find order with id: " + id)));
    }

    @Override
    public Order update(Order order) {
        Order updateOrder = get(order.getOrderId()).get();
        updateOrder.setItems(order.getItems());
        updateOrder.setSumOfMoney(order.getSumOfMoney());
        updateOrder.setUserId(order.getUserId());
        return order;
    }

    @Override
    public boolean deleteById(Long id) {
        Optional<Order> deletedOrder = Optional.ofNullable(Storage.orders.stream()
                .filter(order -> order.getOrderId().equals(id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(
                        "Can't find item with id " + id)));
        return Storage.orders.remove(deletedOrder.get());
    }

    @Override
    public boolean delete(Order order) {
        return Storage.orders.remove(order);
    }

    @Override
    public List<Order> getAll() {
        return Storage.orders;
    }
}
