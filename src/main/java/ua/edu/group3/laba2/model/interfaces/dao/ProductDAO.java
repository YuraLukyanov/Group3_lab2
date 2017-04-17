package ua.edu.group3.laba2.model.interfaces.dao;

import ua.edu.group3.laba2.model.pojo.Product;

import java.util.Collection;

public interface ProductDAO {
    int insertProduct(Product product);

    boolean deleteProduct(int id);

    Product findProduct(int id);

    boolean updateProduct(int id, Product newProduct);

    Collection<Product> selectProductTO(Product filterProduct);
}
