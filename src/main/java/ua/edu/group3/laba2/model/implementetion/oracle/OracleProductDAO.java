package ua.edu.group3.laba2.model.implementetion.oracle;

import ua.edu.group3.laba2.model.interfaces.ProductDAO;
import ua.edu.group3.laba2.model.pojo.Product;

import javax.sql.RowSet;
import java.util.Collection;

/**
 * Created by user on 03.04.2017.
 */
public class OracleProductDAO implements ProductDAO {
    public int insertProduct() {
        return 0;
    }

    public boolean deleteProduct(int id) {
        return false;
    }

    public Product findProduct(int id) {
        return null;
    }

    public boolean updateProduct(int id) {
        return false;
    }

    public RowSet selectProductRS() {
        return null;
    }

    public Collection selectProductTO() {
        return null;
    }
}
