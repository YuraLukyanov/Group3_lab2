package ua.edu.group3.laba2.model.dao;

import ua.edu.group3.laba2.model.pojo.Product;

import javax.sql.RowSet;
import java.util.Collection;

//TODO
public interface ProductDAO {
    int insertProduct();
    boolean deleteProduct(int id);
    Product findProduct(int id);
    boolean updateProduct(int id);
    RowSet selectProductRS();
    Collection selectProductTO();
}
