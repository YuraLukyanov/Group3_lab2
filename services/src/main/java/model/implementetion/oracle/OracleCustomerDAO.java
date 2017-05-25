package model.implementetion.oracle;

import model.implementetion.oracle.exceptions.DBConnectionException;
import model.implementetion.oracle.exceptions.WrongIDException;
import model.interfaces.dao.CustomerDAO;
import model.pojo.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OracleCustomerDAO implements CustomerDAO{
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleCustomerDAO.class);

    public int insert(Customer customer) throws Exception{
        int indexOfAddedElement;

        try {
            String sql =
                    "INSERT INTO Customer (id, name, login, password) VALUES (NULL, ?, ?, ?)";

            Connection connection = OracleDAOFactory.createConnection();

            PreparedStatement preparedStatement =
                    connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getLogin());
            preparedStatement.setString(3, customer.getPassword());
            preparedStatement.execute();

            //getting id of just added element from sequence
            preparedStatement = connection
                    .prepareStatement("select CUSTOMER_AI.currval from dual");

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                indexOfAddedElement = (int) resultSet.getLong(1);
            } else throw new Exception("Can't get id of just added customer.");

        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return indexOfAddedElement;
    }

    public boolean delete(int id) throws Exception {
        find(id);  //checking - does element with this id exist in DB

        try {
            Connection connection = OracleDAOFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Customer WHERE id = " + id);
            preparedStatement.execute();

        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return true;
    }

    public Customer find(int id) throws Exception {
        Customer customer = new Customer();

        try {
            String query = "SELECT * FROM Customer WHERE id = " + id;
            Connection connection = OracleDAOFactory.createConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (!resultSet.next())
                throw new WrongIDException();

            customer.setName(resultSet.getString("name"));
            customer.setLogin(resultSet.getString("login"));
            customer.setPassword(resultSet.getString("password"));
        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return customer;
    }

    public boolean update(int id, Customer newCustomer) throws Exception {
        find(id);   //checking - does element with this id exist in DB

        try {
            Connection connection = OracleDAOFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Product SET NAME = ?, login = ?, password = ? WHERE id = " + id);
            preparedStatement.setString(1, newCustomer.getName());
            preparedStatement.setString(2, newCustomer.getLogin());
            preparedStatement.setString(3, newCustomer.getPassword() + "");
            preparedStatement.execute();

        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return true;
    }

    public Collection<Customer> selectTO(Customer filter) throws Exception {
        ArrayList<Customer> customers = new ArrayList<>();

        try {
            Connection connection = OracleDAOFactory.createConnection();

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
        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return customers;
    }

}
