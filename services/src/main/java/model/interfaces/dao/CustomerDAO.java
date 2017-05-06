package model.interfaces.dao;

import model.pojo.Customer;

import java.util.Collection;


public interface CustomerDAO {
    int insert(Customer customer);

    boolean delete(int id);

    Customer find(int id);

    boolean update(int id, Customer newCustomer);

    Collection<Customer> selectTO(Customer filterCustomer);
}
