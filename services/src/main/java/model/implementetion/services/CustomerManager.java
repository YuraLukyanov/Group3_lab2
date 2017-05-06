package model.implementetion.services;

import model.DAOFactory;
import model.interfaces.dao.CustomerDAO;
import model.interfaces.services.ICustomerManager;
import model.pojo.Customer;

import java.util.Collection;

public class CustomerManager implements ICustomerManager{
    public Customer get(int id) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.find(id);
    }

    public Collection<Customer> getByName(String name) {
        Customer filter = new Customer();
        filter.setName(name);
        return getByFilter(filter);
    }

    public Collection<Customer> getAll() {
        return getByFilter(null);
    }

    public boolean update(int id, Customer newCustomer) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.update(id, newCustomer);
    }

    public int add(Customer customer) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.insert(customer);
    }

    public boolean delete(int id) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.delete(id);
    }

    public Collection<Customer> getByFilter(Customer filter) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();
        return customerDAO.selectTO(filter);
    }
}