package ua.edu.group3.laba2.model.implementetion.oracle;

import ua.edu.group3.laba2.model.interfaces.dao.ProductDAO;
import ua.edu.group3.laba2.model.pojo.Product;

import javax.sql.RowSet;
import java.util.Collection;

public class OracleProductDAO implements ProductDAO {
    public int insertProduct(Product product) {
        return 0;
    }

    public boolean deleteProduct(int id) {
        return false;
    }

    public Product findProduct(int id) {
        return null;
    }

    public boolean updateProduct(int id, Product newProduct) {
        return false;
    }

    public Collection<Product> selectProductTO(Product filterProduct) {
        return null;
    }

}
