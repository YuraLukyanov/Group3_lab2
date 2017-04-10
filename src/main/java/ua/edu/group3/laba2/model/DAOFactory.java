package ua.edu.group3.laba2.model;

import ua.edu.group3.laba2.model.interfaces.CustomerDAO;
import ua.edu.group3.laba2.model.interfaces.OrderDAO;
import ua.edu.group3.laba2.model.interfaces.ProductDAO;
import ua.edu.group3.laba2.model.implementetion.oracle.OracleDAOFactory;

public abstract class DAOFactory {

    public abstract CustomerDAO getCustomerDAO();
    public abstract ProductDAO getProductDAO();
    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory() {
         return new OracleDAOFactory();
    }
}
