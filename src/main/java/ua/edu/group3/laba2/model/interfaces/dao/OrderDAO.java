package ua.edu.group3.laba2.model.interfaces.dao;

import ua.edu.group3.laba2.model.pojo.Order;

import java.util.Collection;

public interface OrderDAO {
    int insertOrder(Order order);

    boolean deleteOrder(int id);

    Order findOrder(int id);

    boolean updateOrder(int id, Order newOrder);

    Collection<Order> selectOrderTO(Order filterOrder);
}
