package model.implementetion.services;

import model.DAOFactory;
import model.interfaces.dao.OrderDAO;
import model.interfaces.services.IOrderManager;
import model.pojo.Order;

import java.util.Collection;

public class OrderManager implements IOrderManager{
    public Order get(int id) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.find(id);
    }

    public Collection<Order> getAll() {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.selectTO(null);
    }

    public boolean update(int id, Order newOrder) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.update(id, newOrder);
    }

    public int add(Order order) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.insert(order);
    }

    public boolean delete(int id) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.delete(id);
    }
}
