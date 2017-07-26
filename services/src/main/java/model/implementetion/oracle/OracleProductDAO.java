package model.implementetion.oracle;

import model.implementetion.oracle.exceptions.WrongIDException;
import model.interfaces.dao.ProductDAO;
import model.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

class OracleProductDAO implements ProductDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleProductDAO.class);

    public int insert(Product product) throws Exception {
        int indexOfAddedElement;

        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        ResultSet resultSet = null;
        try {
            //connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            String query = "INSERT INTO Product (id, name, color, weight, volume, price) VALUES (NULL, ?, ?, ?, ?, ?)";
            statement1 =
                    connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement1.setString(1, product.getName());
            statement1.setString(2, product.getColor());
            statement1.setInt(3, product.getWeight());
            statement1.setInt(4, product.getVolume());
            statement1.setInt(5, product.getPrice());
            statement1.execute();

            connection.commit();

        } catch (SQLException exception) {
            connection.rollback();
            Util.close(statement1);
            OracleDAOFactory.releaseConnection(connection);
            throw exception;
        }

        try {
            //getting id of just added element from sequence
            statement2 = connection.prepareStatement("SELECT PRODUCT_AI.currval FROM dual");
            resultSet = statement2.executeQuery();

            if (resultSet.next()) {
                indexOfAddedElement = (int) resultSet.getLong(1);
            } else throw new Exception("Can't get id of just added product.");

            return indexOfAddedElement;
        } catch (Exception exception) {
            connection.rollback();
            throw exception;
        } finally {
            Util.close(resultSet);
            Util.close(statement1);
            Util.close(statement2);
            OracleDAOFactory.releaseConnection(connection);
        }
    }

    public boolean delete(int id) throws Exception {
        //checking - does element with this id exist in DB
        find(id);  //throws WrongIDException if it doesn't exist

        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM Product WHERE id = ?");
            statement.setInt(1, id);
            statement.execute();
            connection.commit();
            return true;
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        } finally {
            Util.close(statement);
            OracleDAOFactory.releaseConnection(connection);
        }
    }

    /**
     * Method uses try with resources. It close all of the resources for sure
     *
     *
     * @param id of product to find
     * @return product
     * @throws Exception: WrongIDException if product with this id doesn't exist
     *                    SQL exception if something wrong with SQL
     */
    public Product find(int id) throws Exception {
        String query = "SELECT * FROM Product WHERE id = ?";
        Connection connection = null;
        try {
            connection = OracleDAOFactory.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {

                    if (!resultSet.next())
                        throw new WrongIDException();

                    Product result = new Product();
                    result.setId(resultSet.getInt("id"));
                    result.setName(resultSet.getString("name"));
                    result.setColor(resultSet.getString("color"));
                    result.setWeight(resultSet.getInt("weight"));
                    result.setVolume(resultSet.getInt("volume"));
                    result.setPrice(resultSet.getInt("price"));

                    connection.commit();
                    return result;
                } catch (SQLException | WrongIDException exception) {
                    connection.rollback();
                    throw exception;
                }
            }
        } finally {
            if (connection != null) {
                OracleDAOFactory.releaseConnection(connection);
            }
        }
    }

    public boolean update(int id, Product newProduct) throws Exception {
        find(id);   //checking - does element with this id exist in DB

        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement("UPDATE Product" +
                    " SET NAME = ?, color = ?, weight = ?, volume = ?, price = ? WHERE id = " + id);
            statement.setString(1, newProduct.getName());
            statement.setString(2, newProduct.getColor());
            statement.setInt(3, newProduct.getWeight());
            statement.setInt(4, newProduct.getVolume());
            statement.setInt(5, newProduct.getPrice());
            statement.execute();

            connection.commit();
            return true;
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        } finally {
            Util.close(statement);
            OracleDAOFactory.releaseConnection(connection);
        }
    }

    public Collection<Product> selectTO(Product filter) throws Exception {
        ArrayList<Product> result = new ArrayList<>();

        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        StringBuilder query = new StringBuilder("SELECT * FROM Product");

        if (filter != null) {
            query.append(" WHERE ");
            boolean condition = false;  //to indicate does WHERE statement already have conditions

            if (filter.getName() != null) {
                query.append("name = '");
                query.append(filter.getName());
                query.append("'");
                condition = true;
            }
            if (filter.getColor() != null) {
                if (condition) {
                    query.append(" and ");
                }
                query.append("color = '");
                query.append(filter.getColor());
                query.append("'");
                condition = true;
            }
            if (filter.getWeight() != -1) {
                if (condition) {
                    query.append(" and ");
                }
                query.append("weight = ");
                query.append(filter.getWeight());
                condition = true;
            }
            if (filter.getVolume() != -1) {
                if (condition) {
                    query.append(" and ");
                }
                query.append("volume = ");
                query.append(filter.getVolume());
                condition = true;
            }
            if (filter.getPrice() != -1) {
                if (condition) {
                    query.append(" and ");
                }
                query.append("price = ");
                query.append(filter.getPrice());
            }
        }

        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);
            statement = connection.prepareStatement(query.toString());
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setColor(resultSet.getString("color"));
                product.setWeight(resultSet.getInt("weight"));
                product.setVolume(resultSet.getInt("volume"));
                product.setPrice(resultSet.getInt("price"));
                result.add(product);
            }

            connection.commit();
            return result;
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        } finally {
            Util.close(resultSet);
            Util.close(statement);
            OracleDAOFactory.releaseConnection(connection);
        }
    }

    public boolean deleteAll() throws Exception {
        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement statement = null;
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            //connection.setAutoCommit(false);
            statement = connection.prepareStatement("DELETE FROM Product");
            statement.execute();
            //connection.commit();
            return true;
        } catch (SQLException exception) {
            connection.rollback();
            throw exception;
        } finally {
            Util.close(statement);
            OracleDAOFactory.releaseConnection(connection);
        }
    }
}
