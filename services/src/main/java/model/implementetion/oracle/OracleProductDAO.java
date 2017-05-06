package model.implementetion.oracle;

import model.implementetion.oracle.exceptions.DBConnectionException;
import model.interfaces.dao.ProductDAO;
import model.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class OracleProductDAO implements ProductDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleDAOFactory.class);

    public int insert(Product product) throws Exception {
        int indexOfAddedElement = -1;

        try {
            Connection connection = OracleDAOFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO Product (id, name, color, weight, volume, price) VALUES (NULL, ?, ?, ?, ?, ?)");
            preparedStatement.setString(1, product.getName());
            preparedStatement.setString(2, product.getColor());
            preparedStatement.setString(3, product.getWeight() + "");
            preparedStatement.setString(4, product.getVolume() + "");
            preparedStatement.setString(5, product.getPrice() + "");
            preparedStatement.execute();

            //TODO: indexOfAddedElement = ...

        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } catch (SQLException sqlexception) {
            LOGGER.error(sqlexception.toString());
            throw new Exception(sqlexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return indexOfAddedElement;
    }

    public boolean delete(int id) throws Exception {
        try {
            Connection connection = OracleDAOFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM Product WHERE id = ?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            //TODO: indexOfAddedElement = ...

        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } catch (SQLException sqlexception) {
            LOGGER.error(sqlexception.toString());
            throw new Exception(sqlexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return true;
    }

    public Product find(int id) throws Exception {
        Product product = new Product();

        try {
            Connection connection = OracleDAOFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT Product.name, Product.color, Product.weight, Product.volume, Product.price" +
                            "FROM Product WHERE id = ?");
            ResultSet resultSet = preparedStatement.executeQuery();

            product.setName(resultSet.getString("name"));
            product.setColor(resultSet.getString("color"));
            product.setWeight(resultSet.getInt("weight"));
            product.setVolume(resultSet.getInt("volume"));
            product.setPrice(resultSet.getInt("price"));
        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } catch (SQLException sqlexception) {
            LOGGER.error(sqlexception.toString());
            throw new Exception(sqlexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return product;
    }

    public boolean update(int id, Product newProduct) throws Exception {
        try {
            Connection connection = OracleDAOFactory.createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE Product SET NAME = ?, color = ?, weight = ?, volume = ?, price = ? WHERE id = ?");
            preparedStatement.setString(1, newProduct.getName());
            preparedStatement.setString(2, newProduct.getColor());
            preparedStatement.setString(3, newProduct.getWeight() + "");
            preparedStatement.setString(4, newProduct.getVolume() + "");
            preparedStatement.setString(5, newProduct.getPrice() + "");
            preparedStatement.execute();

        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } catch (SQLException sqlexception) {
            LOGGER.error(sqlexception.toString());
            throw new Exception(sqlexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return true;
    }

    public Collection<Product> selectTO(Product filter) throws Exception {
        ArrayList<Product> products = new ArrayList<Product>();

        try {
            Connection connection = OracleDAOFactory.createConnection();

            String statement = "SELECT Product.name, Product.color, Product.weight, " +
                    "Product.volume, Product.price FROM Product ";

            if (filter != null) {
                statement += "WHERE ";

                if (filter.getName() != null) {
                    statement += "name = " + filter.getName();
                }
                if (filter.getColor() != null) {
                    statement += "color = " + filter.getColor();
                }
                if (filter.getWeight() != 0) {
                    statement += "weight = " + filter.getWeight();
                }
                if (filter.getVolume() != 0) {
                    statement += "volume = " + filter.getVolume();
                }
                if (filter.getPrice() != 0) {
                    statement += "price = " + filter.getPrice();
                }
            }

            PreparedStatement preparedStatement = connection.prepareStatement(statement);
            ResultSet resultSet = preparedStatement.executeQuery();

            while(resultSet.next()) {
                Product product = new Product();
                product.setName(resultSet.getString("name"));
                product.setColor(resultSet.getString("color"));
                product.setWeight(resultSet.getInt("weight"));
                product.setVolume(resultSet.getInt("volume"));
                product.setPrice(resultSet.getInt("price"));
                products.add(product);
            }
        } catch (DBConnectionException dbexception) {
            LOGGER.error(dbexception.toString());
            throw new Exception(dbexception);
        } catch (SQLException sqlexception) {
            LOGGER.error(sqlexception.toString());
            throw new Exception(sqlexception);
        } finally {
            OracleDAOFactory.closeConnection();
        }

        return products;
    }

}
