package ua.edu.group3.laba2.model.dao;

import ua.edu.group3.laba2.model.oracle_dao.OracleDAOFactory;

public abstract class DAOFactory {

    public abstract CustomerDAO getCustomerDAO();
    public abstract ProductDAO getProductDAO();
    public abstract OrderDAO getOrderDAO();

    public static DAOFactory getDAOFactory() {
         return new OracleDAOFactory();
    }
}
