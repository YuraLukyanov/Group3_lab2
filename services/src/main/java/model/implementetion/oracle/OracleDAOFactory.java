package model.implementetion.oracle;

import model.DAOFactory;
import model.implementetion.oracle.exceptions.DBConnectionException;
import model.interfaces.dao.CustomerDAO;
import model.interfaces.dao.OrderDAO;
import model.interfaces.dao.ProductDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;


public class OracleDAOFactory extends DAOFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleDAOFactory.class);

    private static Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private static ResultSet resultSet = null;

    protected static Connection createConnection() throws DBConnectionException {
        try {
            String driver = ""; //TODO: get from properties
            String url = ""; //TODO: get from properties
            String user = "User";
            String password = "0660669819";

            Class.forName(driver); // альтернатива Driver driver = new OracleDriver();

            connection = DriverManager.getConnection(url, user, password);

            if (!connection.isClosed())
                LOGGER.info("Connection successful");
        } catch (Exception exception) {
            LOGGER.error("Connecting to DB exception:" + exception.toString());
            throw new DBConnectionException();
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
