package model.interfaces.services;

import model.pojo.Order;

import java.util.Collection;

public interface IOrderManager {
    Order get(int id);
    Collection<Order> getAll();
    int add();
    boolean delete(int id);
}
