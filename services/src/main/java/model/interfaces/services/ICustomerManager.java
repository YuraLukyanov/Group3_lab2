package model.interfaces.services;

import model.pojo.Customer;

import java.util.Collection;

public interface ICustomerManager {
    Customer get(int id) throws Exception;
    Collection <Customer> getByName(String name) throws Exception;
    Collection <Customer> getAll() throws Exception;
    boolean update(int id, String name, String login, String password) throws Exception;
    int add(String name, String login, String password) throws Exception;
    boolean delete(int id) throws Exception;

}
