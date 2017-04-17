package ua.edu.group3.laba2.model.implementetion.oracle;

import ua.edu.group3.laba2.model.interfaces.dao.OrderDAO;
import ua.edu.group3.laba2.model.pojo.Order;

import javax.sql.RowSet;
import java.util.Collection;

public class OracleOrderDAO implements OrderDAO {
    public int insertOrder(Order order) {
        return 0;
    }

    public boolean deleteOrder(int id) {
        return false;
    }

    public Order findOrder(int id) {
        return null;
    }

    public boolean updateOrder(int id, Order newOrder) {
        return false;
    }

    public Collection<Order> selectOrderTO(Order filterOrder) {
        return null;
    }
}
