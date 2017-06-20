package model.implementetion.oracle;

import model.implementetion.oracle.exceptions.WrongIDException;
import model.interfaces.dao.CustomerDAO;
import model.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

class OracleCustomerDAO implements CustomerDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleCustomerDAO.class);

    public int insert(Customer customer) throws Exception {
        int indexOfAddedElement;

        String sql =
                "INSERT INTO Customer (id, name, login, password) VALUES (CUSTOMER_AI.nextval, ?, ?, ?)";

        Connection connection = OracleDAOFactory.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, customer.getName());
        preparedStatement.setString(2, customer.getLogin());
        preparedStatement.setString(3, customer.getPassword());
        preparedStatement.execute();

        //getting id of just added element from sequence
        preparedStatement = connection
                .prepareStatement("SELECT CUSTOMER_AI.currval FROM dual");

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            indexOfAddedElement = (int) resultSet.getLong(1);
        } else throw new Exception("Can't get id of just added customer.");

        resultSet.close();
        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return indexOfAddedElement;
    }

    public boolean delete(int id) throws Exception {
        find(id);  //checking - does element with this id exist in DB

        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Customer WHERE id = " + id);
        preparedStatement.execute();

        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return true;
    }

    public Customer find(int id) throws Exception {
        Customer customer = new Customer();

        String query = "SELECT * FROM Customer WHERE id = " + id;
        Connection connection = OracleDAOFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (!resultSet.next())
            throw new WrongIDException();

        customer.setName(resultSet.getString("name"));
        customer.setLogin(resultSet.getString("login"));
        customer.setPassword(resultSet.getString("password"));


        resultSet.close();
        statement.close();
        OracleDAOFactory.releaseConnection(connection);

        return customer;
    }

    public boolean update(int id, Customer newCustomer) throws Exception {
        find(id);   //checking - does element with this id exist in DB

        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Product SET NAME = ?, login = ?, password = ? WHERE id = " + id);
        preparedStatement.setString(1, newCustomer.getName());
        preparedStatement.setString(2, newCustomer.getLogin());
        preparedStatement.setString(3, newCustomer.getPassword() + "");
        preparedStatement.execute();

        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return true;
    }

    public Collection<Customer> selectTO(Customer filter) throws Exception {
        ArrayList<Customer> customers = new ArrayList<>();

        Connection connection = OracleDAOFactory.getConnection();

        String statement = "SELECT * FROM Customer";

        if (filter != null) {
            statement += " WHERE ";
            boolean condition = false;

            if (filter.getName() != null) {
                statement += "name = " + filter.getName();
                condition = true;
            }
            if (filter.getLogin() != null) {
                if (condition) {
                    statement += " and ";
                }
                statement += "login = " + filter.getLogin();
                condition = true;
            }
            if (filter.getPassword() != null) {
                if (condition) {
                    statement += " and ";
                }
                statement += "password = " + filter.getPassword();
            }
        }

        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Customer customer = new Customer();
            customer.setName(resultSet.getString("name"));
            customer.setLogin(resultSet.getString("login"));
            customer.setPassword(resultSet.getString("password"));
            customers.add(customer);
        }

        resultSet.close();
        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return customers;
    }

}
