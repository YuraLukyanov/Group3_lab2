package ua.edu.group3.laba2.model.interfaces.services;

import ua.edu.group3.laba2.model.pojo.Customer;

import java.util.Collection;

public interface ICustomerManager {
    Customer get(int id);
    Customer getByLogin(String login);
    Collection <Customer> getByName(String name);
    Collection <Customer> getAll();
    boolean update(int id, Customer newCustomer);
    int add(Customer customer);
    boolean delete(int id);
}
