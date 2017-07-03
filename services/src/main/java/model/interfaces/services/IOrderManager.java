package model.interfaces.services;

import model.pojo.Order;

import java.util.Collection;

public interface IOrderManager {

    void setBusket(IBusket busket);

    Order get(int id) throws Exception;

    Collection<Order> getByCustomer(String login) throws Exception;

    Collection<Order> getAll() throws Exception;

    int add() throws Exception;

    boolean delete(int id) throws Exception;

    boolean deleteAll() throws Exception;
}
