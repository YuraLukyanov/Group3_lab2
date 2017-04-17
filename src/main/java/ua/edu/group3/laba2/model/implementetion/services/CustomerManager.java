package ua.edu.group3.laba2.model.implementetion.services;

import ua.edu.group3.laba2.model.interfaces.services.ICustomerManager;
import ua.edu.group3.laba2.model.pojo.Customer;

import java.util.Collection;

public class CustomerManager implements ICustomerManager{
    public Customer get(int id) {
        return null;
    }

    public Customer getByLogin(String login) {
        return null;
    }

    public Collection<Customer> getByName(String name) {
        return null;
    }

    public Collection<Customer> getAll() {
        return null;
    }

    public boolean update(int id, Customer newCustomer) {
        return false;
    }

    public int add(Customer customer) {
        return 0;
    }

    public boolean delete(int id) {
        return false;
    }
}
