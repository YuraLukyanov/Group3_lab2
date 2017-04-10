package ua.edu.group3.laba2.model.interfaces;

import ua.edu.group3.laba2.model.pojo.Order;

import javax.sql.RowSet;
import java.util.Collection;

//TODO
public interface OrderDAO {
    int insertOrder();
    boolean deleteOrder(int id);
    Order findOrder(int id);
    boolean updateOrder(int id);
    RowSet selectOrderRS();
    Collection selectOrderTO();
}
