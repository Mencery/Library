package repository;

import db.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {
    private List<Order> orders;

    public OrderRepository(List<Order> orders) {
        this.orders = orders;
    }

    public void add(Order order) {
        orders.add(order);
    }

    public List<Order> getByUserId(final int userId) {
        List<Order> result = new ArrayList<>();
        orders.stream().filter(order -> order.getReaderId() == userId).forEach(result::add);
        return result;
    }

    public List<Order> getByBookId(int bookId) {
        List<Order> result = new ArrayList<>();
        orders.stream().filter(order -> order.getBookId() == bookId).forEach(result::add);
        return result;
    }

    public List<Order> getOrders() {
        return orders;
    }
}
