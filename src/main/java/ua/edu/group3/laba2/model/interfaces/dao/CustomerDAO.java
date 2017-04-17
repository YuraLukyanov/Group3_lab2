package ua.edu.group3.laba2.model.interfaces.dao;

import ua.edu.group3.laba2.model.pojo.Customer;

import java.util.Collection;


public interface CustomerDAO {
    int insertCustomer(Customer customer);

    boolean deleteCustomer(int id);

    Customer findCustomer(int id);

    boolean updateCustomer(int id, Customer newCustomer);

    Collection<Customer> selectCustomersTO(Customer filterCustomer);
}
