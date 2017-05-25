package model.interfaces.dao;

import model.implementetion.oracle.exceptions.WrongIDException;
import model.pojo.Customer;

import java.sql.SQLException;
import java.util.Collection;


public interface CustomerDAO {
    int insert(Customer customer) throws Exception;

    boolean delete(int id) throws Exception;

    Customer find(int id) throws Exception;

    boolean update(int id, Customer newCustomer) throws Exception;

    Collection<Customer> selectTO(Customer filterCustomer) throws Exception;
}
