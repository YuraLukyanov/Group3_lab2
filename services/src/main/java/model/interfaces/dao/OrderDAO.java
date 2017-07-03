package model.interfaces.dao;

import model.pojo.Order;

import java.sql.SQLException;
import java.util.Collection;

public interface OrderDAO {
    int insert(Order order) throws Exception;

    boolean delete(int id) throws Exception;

    Order find(int id) throws Exception;

    Collection<Order> selectTO(String customerLogin) throws Exception;

    boolean deleteAll() throws Exception;
}
