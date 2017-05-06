package model.interfaces.dao;

import model.pojo.Product;

import java.util.Collection;

public interface ProductDAO {
    int insert(Product product) throws Exception;

    boolean delete(int id) throws Exception;

    Product find(int id) throws Exception;

    boolean update(int id, Product newProduct) throws Exception;

    Collection<Product> selectTO(Product filterProduct) throws Exception;
}
