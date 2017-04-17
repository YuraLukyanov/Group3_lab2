package ua.edu.group3.laba2.model.implementetion.oracle;

import ua.edu.group3.laba2.model.interfaces.dao.CustomerDAO;
import ua.edu.group3.laba2.model.pojo.Customer;

import javax.sql.RowSet;
import java.sql.Connection;
import java.util.Collection;

public class OracleCustomerDAO implements CustomerDAO {

    public int insertCustomer(Customer customer) {
        Connection connection = OracleDAOFactory.createConnection();
        //TODO
        OracleDAOFactory.closeConnection();

        return 0;
    }

    public boolean deleteCustomer(int id) {
        return false;
    }

    public Customer findCustomer(int id) {
        return null;
    }

    public boolean updateCustomer(int id, Customer newCustomer) {
        return false;
    }

    public Collection<Customer> selectCustomersTO(Customer filterCustomer) {
        return null;
    }

}
