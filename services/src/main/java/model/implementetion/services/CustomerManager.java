package model.implementetion.services;

import model.DAOFactory;
import model.interfaces.dao.CustomerDAO;
import model.interfaces.services.ICustomerManager;
import model.pojo.Customer;

import java.util.Collection;

public class CustomerManager implements ICustomerManager{
    public Customer get(int id) throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.find(id);
    }

    public Collection<Customer> getByName(String name) throws Exception {
        Customer filter = new Customer();
        filter.setName(name);
        return getByFilter(filter);
    }

    public Collection<Customer> getAll() throws Exception {
        return getByFilter(null);
    }

    public boolean update(int id, String name, String login, String password) throws Exception {
        Customer newCustomer = new Customer(name, login, password);
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.update(id, newCustomer);
    }

    public int add(String name, String login, String password) throws Exception {
        Customer customer = new Customer(name, login, password);
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.insert(customer);
    }

    public boolean delete(int id) throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.delete(id);
    }

    private Collection<Customer> getByFilter(Customer filter) throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.selectTO(filter);
    }
}