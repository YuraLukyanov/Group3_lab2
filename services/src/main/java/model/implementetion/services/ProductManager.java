package model.implementetion.services;

import model.DAOFactory;
import model.interfaces.dao.ProductDAO;
import model.interfaces.services.IProductManager;
import model.pojo.Product;

import java.util.Collection;

public class ProductManager implements IProductManager {
    public Product get(int id) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        return productDAO.find(id);
    }

    public Collection<Product> getByName(String name) {
        Product filter = new Product();
        filter.setName(name);
        return getByFilter(filter);
    }

    public Collection<Product> getByColor(String color) {
        Product filter = new Product();
        filter.setColor(color);
        return getByFilter(filter);
    }

    public Collection<Product> getByVolume(int volume) {
        Product filter = new Product();
        filter.setVolume(volume);
        return getByFilter(filter);
    }

    public Collection<Product> getByWeight(int weight) {
        Product filter = new Product();
        filter.setWeight(weight);
        return getByFilter(filter);
    }

    public Collection<Product> getByPrice(int price) {
        Product filter = new Product();
        filter.setPrice(price);
        return getByFilter(filter);
    }

    public Collection<Product> getAll() {
        return getByFilter(null);
    }

    public boolean update(int id, Product newProduct) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        return productDAO.update(id, newProduct);
    }

    public int add(Product product) throws Exception {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        return productDAO.insert(product);
    }

    public boolean delete(int id) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        return productDAO.delete(id);
    }

    private Collection<Product> getByFilter(Product filter) {
        DAOFactory factory = DAOFactory.getDAOFactory();
        ProductDAO productDAO = factory.getProductDAO();
        return productDAO.selectTO(filter);
    }
}
