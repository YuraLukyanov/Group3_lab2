package model;

import model.implementetion.oracle.OracleDAOFactory;
import model.interfaces.dao.CustomerDAO;
import model.interfaces.dao.OrderDAO;
import model.interfaces.dao.ProductDAO;

public abstract class DAOFactory {

    public abstract CustomerDAO getCustomerDAO();
    public abstract ProductDAO getProductDAO();
    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory() {
         return new OracleDAOFactory();
    }
}
