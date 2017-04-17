package ua.edu.group3.laba2.model.implementetion.oracle;

import ua.edu.group3.laba2.model.interfaces.dao.CustomerDAO;
import ua.edu.group3.laba2.model.DAOFactory;
import ua.edu.group3.laba2.model.interfaces.dao.OrderDAO;
import ua.edu.group3.laba2.model.interfaces.dao.ProductDAO;

import java.sql.*;


public class OracleDAOFactory extends DAOFactory {


    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    protected static Connection createConnection() {
        try {
            String driver = ""; //TODO: get from properties
            String url = ""; //TODO: get from properties
            String user = "User"; //TODO: getUser (ask mentor)!
            String password = "0660669819"; //TODO: getPassword (ask mentor)!

            Class.forName(driver); // альтернатива Driver driver = new OracleDriver();

            connection = DriverManager.getConnection(url, user, password);

            if (!connection.isClosed())
                System.out.println("Connected successful ..."); //TODO: print to log
        } catch (Exception e) {
            e.printStackTrace(); //TODO: print to log
        }

        return connection;
    }

    protected static void closeConnection(){
        try {
            if(preparedStatement != null)
                preparedStatement.close();
            if(connection != null)
                connection.close();
            if(resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace(); //TODO: print to log
        }
    }

    public CustomerDAO getCustomerDAO() {
        return new OracleCustomerDAO();
    }
    public ProductDAO getProductDAO() {
        return new OracleProductDAO();
    }
    public OrderDAO getOrderDAO() {
        return new OracleOrderDAO();
    }
}
