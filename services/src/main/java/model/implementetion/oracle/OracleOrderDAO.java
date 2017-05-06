package model.implementetion.oracle;

import model.interfaces.dao.OrderDAO;
import model.pojo.Order;

import java.util.Collection;

public class OracleOrderDAO implements OrderDAO {
    public int insert(Order order) {
        return 0;
    }

    public boolean delete(int id) {
        return false;
    }

    public Order find(int id) {
        return null;
    }

    public boolean update(int id, Order newOrder) {
        return false;
    }

    public Collection<Order> selectTO(Order filterOrder) {
        return null;
    }
}
