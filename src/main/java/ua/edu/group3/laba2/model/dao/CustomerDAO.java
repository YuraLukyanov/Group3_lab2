package ua.edu.group3.laba2.model.dao;

import ua.edu.group3.laba2.model.pojo.Customer;

import javax.sql.RowSet;
import java.util.Collection;

//TODO
public interface CustomerDAO {
    int insertCustomer();
    boolean deleteCustomer(int id);
    Customer findCustomer(int id);
    boolean updateCustomer(int id);
    RowSet selectCustomersRS();
    Collection selectCustomersTO();
}
