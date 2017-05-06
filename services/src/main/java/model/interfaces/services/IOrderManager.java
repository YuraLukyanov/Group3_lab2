package model.interfaces.services;

import model.pojo.Order;

import java.util.Collection;

public interface IOrderManager {
    Order get(int id);
    Collection<Order> getAll();
    boolean update(int id, Order newOrder);
    int add(Order order);
    boolean delete(int id);
}
