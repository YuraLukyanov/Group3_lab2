package model.interfaces.services;

import model.pojo.Customer;

import java.util.Collection;

public interface ICustomerManager {
    Customer get(int id);
    Collection <Customer> getByName(String name);
    Collection <Customer> getAll();
    boolean update(int id, String name, String login, String password);
    int add(String name, String login, String password);
    boolean delete(int id);

}
