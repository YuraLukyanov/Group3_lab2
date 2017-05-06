package model.interfaces.services;

import model.pojo.Customer;

import java.util.Collection;

public interface ICustomerManager {
    Customer get(int id);
    Collection <Customer> getByName(String name);
    Collection <Customer> getByFilter(Customer filter);
    Collection <Customer> getAll();
    boolean update(int id, Customer newCustomer);
    int add(Customer customer);
    boolean delete(int id);

}
