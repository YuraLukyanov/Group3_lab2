package ua.edu.group3.laba2.model.implementetion.oracle;

import ua.edu.group3.laba2.model.interfaces.OrderDAO;
import ua.edu.group3.laba2.model.pojo.Order;

import javax.sql.RowSet;
import java.util.Collection;

/**
 * Created by user on 03.04.2017.
 */
public class OracleOrderDAO implements OrderDAO {
    public int insertOrder() {
        return 0;
    }

    public boolean deleteOrder(int id) {
        return false;
    }

    public Order findOrder(int id) {
        return null;
    }

    public boolean updateOrder(int id) {
        return false;
    }

    public RowSet selectOrderRS() {
        return null;
    }

    public Collection selectOrderTO() {
        return null;
    }
}
