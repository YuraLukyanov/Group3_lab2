package model.implementetion.oracle;

import com.google.common.collect.Iterables;
import model.DAOFactory;
import model.implementetion.oracle.exceptions.WrongIDException;
import model.implementetion.services.util.ProductAndAmount;
import model.interfaces.dao.CustomerDAO;
import model.interfaces.dao.OrderDAO;
import model.interfaces.dao.ProductDAO;
import model.pojo.Customer;
import model.pojo.Order;
import model.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

class OracleOrderDAO implements OrderDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleOrderDAO.class);

    public int insert(Order order) throws Exception {
        int orderId;
        int customerId = order.getCustomer().getId();
        int summ = order.getSumm();

        Connection connection = OracleDAOFactory.getConnection();
        String query =
                "INSERT INTO Orders (id, customer_id, summ) " +
                        "VALUES (ORDER_AI.nextval, " + customerId + ", " + summ + ")";

        PreparedStatement preparedStatement =
                connection.prepareStatement(query);

        preparedStatement.execute();

        //getting id of just added order from sequence
        preparedStatement = connection
                .prepareStatement("SELECT ORDER_AI.currval FROM dual");

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) orderId = (int) resultSet.getLong(1);
        else throw new Exception("Can't get id of just added order.");

        for (ProductAndAmount productAndAmount : order.getProductsAndAmounts()) {
            int productId = productAndAmount.getProduct().getId();
            int amount = productAndAmount.getAmount();
            query = "INSERT INTO ProductAndAmount (id, product_id, amount, order_id) " +
                    "VALUES (PRODUCTANDAMOUNT_AI.nextval," + productId + ", " + amount + ", " + orderId + ")";

            preparedStatement = connection.prepareStatement(query);
            preparedStatement.execute();
        }

        resultSet.close();
        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return orderId;
    }

    public boolean delete(int id) throws Exception {
        find(id);  //checking - does element with this id exist in DB

        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Orders WHERE id = " + id);
        preparedStatement.execute();

        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return true;
    }

    public Order find(int id) throws Exception {
        Customer customer;
        int customer_id;
        int summ;

        CustomerDAO customerDAO = DAOFactory.getDAOFactory().getCustomerDAO();

        String query = "SELECT * FROM Orders WHERE id = " + id;
        Connection connection = OracleDAOFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (!resultSet.next()) throw new WrongIDException();

        customer_id = resultSet.getInt("customer_id");
        customer = customerDAO.find(customer_id);

        summ = resultSet.getInt("summ");


        resultSet.close();
        statement.close();
        OracleDAOFactory.releaseConnection(connection);

        return new Order(id, getProductsAndAmounts(id), customer, summ);
    }

    public Collection<Order> selectTO(String customerLogin) throws Exception {
        ArrayList<Order> orders = new ArrayList<>();
        Customer customer = null;
        DAOFactory factory = DAOFactory.getDAOFactory();
        CustomerDAO customerDAO = factory.getCustomerDAO();

        Connection connection = OracleDAOFactory.getConnection();

        String query = "SELECT * FROM Orders";

        if (customerLogin != null) {
            Customer filterCustomer = new Customer();
            filterCustomer.setLogin(customerLogin);
            Collection<Customer> collection = customerDAO.selectTO(filterCustomer);
            customer = Iterables.get(collection, 0);
            query += " WHERE customer_id = " + customer.getId();
        }

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Order order = new Order();
            order.setId(resultSet.getInt("id"));

            if (customerLogin != null) order.setCustomer(customer);
            else {
                int customer_id = resultSet.getInt("customer_id");
                Customer orderCustomer = customerDAO.find(customer_id);
                order.setCustomer(orderCustomer);
            }

            order.setSumm(resultSet.getInt("summ"));

            Collection<ProductAndAmount> productsAndAmounts = getProductsAndAmounts(order.getId());
            order.setProductsAndAmounts(productsAndAmounts);

            orders.add(order);
        }

        resultSet.close();
        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return orders;
    }

    public boolean deleteAll() throws Exception {
        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Orders");
        preparedStatement.execute();

        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return true;
    }

    private Collection<ProductAndAmount> getProductsAndAmounts(int orderId) throws Exception {
        Collection<ProductAndAmount> productsAndAmounts = new ArrayList<>();

        ProductDAO productDAO = DAOFactory.getDAOFactory().getProductDAO();
        Connection connection = OracleDAOFactory.getConnection();
        String query = "SELECT * FROM ProductAndAmount WHERE order_id = " + orderId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            int product_id = resultSet.getInt("product_id");
            Product product = productDAO.find(product_id);
            int amount = resultSet.getInt("amount");

            ProductAndAmount productAndAmount = new ProductAndAmount(product);
            productAndAmount.setAmount(amount);

            productsAndAmounts.add(productAndAmount);
        }

        resultSet.close();
        statement.close();
        OracleDAOFactory.releaseConnection(connection);

        return productsAndAmounts;
    }
}
