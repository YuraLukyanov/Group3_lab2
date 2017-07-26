package model.implementetion.oracle;

import model.implementetion.oracle.exceptions.DBConnectionException;
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
        Exception exception = null;

        int indexOfAddedElement;

        String queryInsert =
                "INSERT INTO Product (id, name, color, weight, volume, price) VALUES (NULL, ?, ?, ?, ?, ?)";

        String queryId = "SELECT PRODUCT_AI.currval FROM dual";

        Connection connection = null;

        try {
            connection = OracleDAOFactory.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            try (PreparedStatement statement1 = connection.prepareStatement(
                    queryInsert, Statement.RETURN_GENERATED_KEYS)) {

                statement1.setString(1, product.getName());
                statement1.setString(2, product.getColor());
                statement1.setInt(3, product.getWeight());
                statement1.setInt(4, product.getVolume());
                statement1.setInt(5, product.getPrice());
                statement1.execute();

                connection.commit();
            } catch (SQLException e) {
                Util.rollback(connection, e, LOGGER);
                throw e;
            }

            //getting id of just added element from sequence
            try (PreparedStatement statement2 = connection.prepareStatement(queryId);
                 ResultSet resultSet = statement2.executeQuery()) {
                connection.commit();

                if (resultSet.next()) {
                    indexOfAddedElement = (int) resultSet.getLong(1);
                } else {
                    throw new SQLException("Can't get id of just added product.");
                }

                return indexOfAddedElement;
            } catch (SQLException e) {
                Util.rollback(connection, e, LOGGER);
                throw e;
            }
        } catch (DBConnectionException | SQLException e) {
            LOGGER.error("Get an exception while trying to insert a product: " + product.toString()
                    + "\n : Exception: " + e.toString());
            exception = e;
            throw exception;
        } finally {
            Util.close(connection, exception, LOGGER);
            if (exception != null) throw exception;
        }
    }

    public boolean delete(int id) throws Exception {
        Exception exception = null;

        //checking - does element with this id exist in DB
        find(id);  //throws WrongIDException if it doesn't exist

        String query = "DELETE FROM Product WHERE id = ?";
        Connection connection = null;
        try {
            connection = OracleDAOFactory.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, id);
                statement.execute();
                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            LOGGER.error("Get an exception while trying to delete row with id = " + id
                    + ": \n" + e.toString());
            try {
                connection.rollback();
            } catch (SQLException ex) {
                e.addSuppressed(ex);
            }

            throw exception = e;
        } finally {
            Util.close(connection, exception, LOGGER);
            if (exception != null) throw exception;
        }

    }

    /**
     * @param id of product to find
     * @return product
     * @throws Exception: WrongIDException if product with this id doesn't exist
     *                    SQL exception if something wrong with SQL
     *                    DBConnectionException if we cant to connect to DB
     */
    public Product find(int id) throws Exception {
        Exception exception = null;

        String query = "SELECT * FROM Product WHERE id = ?";
        Connection connection = null;
        try {
            connection = OracleDAOFactory.getConnection();
            //connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

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

                    return result;
                }
            }
        } catch (DBConnectionException | SQLException e) {
            LOGGER.error("Get an exception while trying to find a row with id = " + id
                    + ":\n" + e.toString());
            throw exception = e;
        } finally {
            Util.close(connection, exception, LOGGER);
            if (exception != null) throw exception;
        }
    }

    public boolean update(int id, Product newProduct) throws Exception {
        Exception exception = null;

        find(id);   //checking - does element with this id exist in DB

        String query =
                "UPDATE Product SET NAME = ?, color = ?, weight = ?, volume = ?, price = ? WHERE id = " + id;

        Connection connection = OracleDAOFactory.getConnection();

        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, newProduct.getName());
                statement.setString(2, newProduct.getColor());
                statement.setInt(3, newProduct.getWeight());
                statement.setInt(4, newProduct.getVolume());
                statement.setInt(5, newProduct.getPrice());
                statement.execute();

                connection.commit();
                return true;
            }
        } catch (SQLException e) {
            Util.rollback(connection, e, LOGGER);
            throw exception = e;
        } finally {
            Util.close(connection, exception, LOGGER);
            if (exception != null) throw exception;
        }
    }

    public Collection<Product> selectTO(Product filter) throws Exception {
        Exception exception = null;

        ArrayList<Product> result = new ArrayList<>();

        Connection connection = null;

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
            connection = OracleDAOFactory.getConnection();
            //connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);

            try (PreparedStatement statement = connection.prepareStatement(query.toString());
                 ResultSet resultSet = statement.executeQuery()) {

                while (resultSet.next()) {
                    Product product = new Product();
                    product.setName(resultSet.getString("name"));
                    product.setColor(resultSet.getString("color"));
                    product.setWeight(resultSet.getInt("weight"));
                    product.setVolume(resultSet.getInt("volume"));
                    product.setPrice(resultSet.getInt("price"));
                    result.add(product);
                }
            }

            return result;
        } catch (SQLException e) {
            throw exception = e;
        } finally {
            Util.close(connection, exception, LOGGER);
            if (exception != null) throw exception;
        }
    }

    public boolean deleteAll() throws Exception {
        Exception exception = null;
        Connection connection = null;
        try {
            connection = OracleDAOFactory.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
            //connection.setAutoCommit(false);

            try (PreparedStatement statement = connection.prepareStatement("DELETE FROM Product")) {
                statement.execute();
                //connection.commit();
                return true;
            }
        } catch (SQLException e) {
            connection.rollback();
            throw exception = e;
        } finally {
            Util.close(connection, exception, LOGGER);
            if (exception != null) throw exception;
        }
    }
}
