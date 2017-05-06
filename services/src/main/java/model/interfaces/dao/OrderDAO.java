package model.interfaces.dao;

import model.pojo.Order;

import java.util.Collection;

public interface OrderDAO {
    int insert(Order order);

    boolean delete(int id);

    Order find(int id);

    boolean update(int id, Order newOrder);

    Collection<Order> selectTO(Order filterOrder);
}
