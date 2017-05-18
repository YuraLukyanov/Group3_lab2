package model.implementetion.services;

import model.DAOFactory;
import model.interfaces.dao.OrderDAO;
import model.interfaces.services.IBusket;
import model.interfaces.services.IOrderManager;
import model.pojo.Order;

import java.util.Collection;

public class OrderManager implements IOrderManager{
    private IBusket busket;

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

    public int add() {
        Order order = busket.getOrder();
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.insert(order);
    }

    public boolean delete(int id) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        OrderDAO orderDAO = factory.getOrderDAO();
        return orderDAO.delete(id);
    }

    public void setBusket(IBusket busket) {
        this.busket = busket;
    }
}
