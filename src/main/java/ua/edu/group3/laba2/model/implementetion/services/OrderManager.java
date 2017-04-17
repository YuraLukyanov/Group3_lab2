package ua.edu.group3.laba2.model.implementetion.services;

import ua.edu.group3.laba2.model.interfaces.services.IOrderManager;
import ua.edu.group3.laba2.model.pojo.Order;

import java.util.Collection;

public class OrderManager implements IOrderManager{
    public Order get(int id) {
        return null;
    }

    public Collection<Order> getAll() {
        return null;
    }

    public boolean update(int id, Order newOrder) {
        return false;
    }

    public int add(Order order) {
        return 0;
    }

    public boolean delete(int id) {
        return false;
    }
}
