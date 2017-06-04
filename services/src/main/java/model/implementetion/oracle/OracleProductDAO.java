package model.implementetion.oracle;

import model.implementetion.oracle.exceptions.WrongIDException;
import model.interfaces.dao.ProductDAO;
import model.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class OracleProductDAO implements ProductDAO {
    private static final Logger LOGGER = LoggerFactory.getLogger(OracleProductDAO.class);

    public int insert(Product product) throws Exception {
        int indexOfAddedElement;

        String query =
                "INSERT INTO Product (id, name, color, weight, volume, price) VALUES (NULL, ?, ?, ?, ?, ?)";

        Connection connection = OracleDAOFactory.getConnection();

        PreparedStatement preparedStatement =
                connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setString(2, product.getColor());
        preparedStatement.setInt(3, product.getWeight());
        preparedStatement.setInt(4, product.getVolume());
        preparedStatement.setInt(5, product.getPrice());
        preparedStatement.execute();

        //getting id of just added element from sequence
        preparedStatement = connection
                .prepareStatement("SELECT PRODUCT_AI.currval FROM dual");

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            indexOfAddedElement = (int) resultSet.getLong(1);
        } else throw new Exception("Can't get id of just added product.");

        resultSet.close();
        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return indexOfAddedElement;
    }

    public boolean delete(int id) throws Exception {
        find(id);  //checking - does element with this id exist in DB

        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM Product WHERE id = " + id);
        preparedStatement.execute();

        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return true;
    }

    public Product find(int id) throws Exception {
        Product product = new Product();

        String query = "SELECT * FROM Product WHERE id = " + id;
        Connection connection = OracleDAOFactory.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        if (!resultSet.next())
            throw new WrongIDException();

        product.setName(resultSet.getString("name"));
        product.setColor(resultSet.getString("color"));
        product.setWeight(resultSet.getInt("weight"));
        product.setVolume(resultSet.getInt("volume"));
        product.setPrice(resultSet.getInt("price"));

        resultSet.close();
        statement.close();
        OracleDAOFactory.releaseConnection(connection);

        return product;
    }

    public boolean update(int id, Product newProduct) throws Exception {
        find(id);   //checking - does element with this id exist in DB

        Connection connection = OracleDAOFactory.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE Product SET NAME = ?, color = ?, weight = ?, volume = ?, price = ? WHERE id = " + id);
        preparedStatement.setString(1, newProduct.getName());
        preparedStatement.setString(2, newProduct.getColor());
        preparedStatement.setInt(3, newProduct.getWeight());
        preparedStatement.setInt(4, newProduct.getVolume());
        preparedStatement.setInt(5, newProduct.getPrice());
        preparedStatement.execute();

        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return true;
    }

    public Collection<Product> selectTO(Product filter) throws Exception {
        ArrayList<Product> products = new ArrayList<>();

        Connection connection = OracleDAOFactory.getConnection();

        String statement = "SELECT * FROM Product";

        if (filter != null) {
            statement += " WHERE ";
            boolean condition = false;  //to indicate does where statement already have conditions

            if (filter.getName() != null) {
                statement += "name = " + filter.getName();
                condition = true;
            }
            if (filter.getColor() != null) {
                if (condition) {
                    statement += " and ";
                }
                statement += "color = " + filter.getColor();
                condition = true;
            }
            if (filter.getWeight() != 0) {
                if (condition) {
                    statement += " and ";
                }
                statement += "weight = " + filter.getWeight();
                condition = true;
            }
            if (filter.getVolume() != 0) {
                if (condition) {
                    statement += " and ";
                }
                statement += "volume = " + filter.getVolume();
                condition = true;
            }
            if (filter.getPrice() != 0) {
                if (condition) {
                    statement += " and ";
                }
                statement += "price = " + filter.getPrice();
            }
        }

        PreparedStatement preparedStatement = connection.prepareStatement(statement);
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Product product = new Product();
            product.setName(resultSet.getString("name"));
            product.setColor(resultSet.getString("color"));
            product.setWeight(resultSet.getInt("weight"));
            product.setVolume(resultSet.getInt("volume"));
            product.setPrice(resultSet.getInt("price"));
            products.add(product);
        }

        resultSet.close();
        preparedStatement.close();
        OracleDAOFactory.releaseConnection(connection);

        return products;
    }
}
