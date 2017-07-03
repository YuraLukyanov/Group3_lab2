package model.implementetion.services;

import model.DAOFactory;
import model.interfaces.dao.OrderDAO;
import model.interfaces.services.IBusket;
import model.interfaces.services.IOrderManager;
import model.pojo.Order;

import java.util.Collection;

public class OrderManager implements IOrderManager{
    private IBusket busket;

    public Order get(int id) throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.find(id);
    }

    public Collection<Order> getByCustomer(String login) throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.selectTO(login);
    }

    public Collection<Order> getAll() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.selectTO(null);
    }

    public int add() throws Exception {
        Order order = busket.getOrder();
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.insert(order);
    }

    public boolean delete(int id) throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.delete(id);
    }

    public boolean deleteAll() throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.deleteAll();
    }

    public void setBusket(IBusket busket) {
        this.busket = busket;
    }
}
