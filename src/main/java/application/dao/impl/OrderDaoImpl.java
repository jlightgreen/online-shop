package application.dao.impl;

import application.dao.OrderDao;
import application.db.Storage;
import application.lib.Dao;
import application.model.Order;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Dao
public class OrderDaoImpl implements OrderDao {
    @Override
    public Order create(Order order) {
        Storage.addOrder(order);
        return order;
    }

    @Override
    public List<Order> getUserOrders(Long userId) {
        return Storage.orders.stream()
                .filter(order -> order.getUserId().equals(userId))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Order> get(Long id) {
        return Storage.getOrderList().stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Order> getAll() {
        return Storage.getOrderList();
    }

    @Override
    public boolean delete(Long id) {
        return Storage.getOrderList().removeIf(order -> order.getId().equals(id));
    }
}
