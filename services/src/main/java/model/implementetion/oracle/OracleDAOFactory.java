package model.implementetion.oracle;

import model.DAOFactory;
import model.implementetion.oracle.exceptions.DBConnectionException;
import model.interfaces.dao.CustomerDAO;
import model.interfaces.dao.OrderDAO;
import model.interfaces.dao.ProductDAO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ConcurrentLinkedQueue;


public class OracleDAOFactory extends DAOFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleDAOFactory.class);
    private static final int POOLDEFSIZE = 20;
    private static final int POOLMAXSIZE = 40;
    private static final int POOLMINSIZE = 5;
    private static final int POOLDELTASIZE = 5;

    private static ConcurrentLinkedQueue<Connection> connectionPool = new ConcurrentLinkedQueue<>();
    private static int size = 0;    //size of pool is in a separate variable because of better performance

    //adding connections to pool
    static {
        try {
            for (int i = 0; i < POOLDEFSIZE; i++) {
                connectionPool.offer(createConnection());
                size++;
            }
        } catch (DBConnectionException exception) {
            LOGGER.error("Can't connect to DB: " + exception.toString());
            throw new RuntimeException(exception);
        }
    }

    protected static Connection getConnection() throws DBConnectionException {
        if (size < POOLMINSIZE) {
            connectionPool.add(createConnection());
            addConnToPoolInNewThread(POOLDELTASIZE);
        }

        size--;
        return connectionPool.poll();
    }

    private static Connection createConnection() throws DBConnectionException {
        Connection connection;

        try {

            URL fileUrl = OracleDAOFactory.class.getResource("/oracle.txt");
            URI fileUri = fileUrl.toURI();
            File file = new File(fileUri);

            FileInputStream fileInputStream = new FileInputStream(file);

            Properties properties = new Properties();
            properties.load(fileInputStream);

            String driver = properties.getProperty("DB_DRIVER_CLASS");
            String url = properties.getProperty("DB_URL");
            String user = properties.getProperty("DB_USERNAME");
            String password = properties.getProperty("DB_PASSWORD");

            fileInputStream.close();

            //initialization of driver class (for sure)
            Class.forName(driver);

            connection = DriverManager.getConnection(url, user, password);

            if (!connection.isClosed())
                LOGGER.info("Connection successful");
        } catch (Exception exception) {
            LOGGER.error("Connecting to DB exception:" + exception.toString());
            throw new DBConnectionException();
        }

        return connection;
    }

    @SuppressWarnings("WeakerAccess")
    protected static void releaseConnection(Connection connection) throws SQLException {
        if (size > POOLMAXSIZE) {
            //delete connections from pool
            deleteConnFromPoolInNewThread();
        }

        connectionPool.offer(connection);
        size++;

    }

    private static void deleteConnFromPoolInNewThread() {
        new Thread(() -> {
            for (int i = 0; i < POOLDELTASIZE; i++) {
                Connection conn = connectionPool.poll();
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOGGER.error("Can't to close a connection! Get an exception: " + e.toString());
                }
                size--;
            }
        });
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

    private static void addConnToPoolInNewThread(int amountOfConnections) {
        new Thread(() -> {
            try {
                for (int i = 0; i < amountOfConnections; i++) {
                    connectionPool.offer(createConnection());
                    size++;
                }
            } catch (DBConnectionException exception) {
                LOGGER.error("Can't connect to DB: " + exception.toString());
            }
        }).start();
    }

}
