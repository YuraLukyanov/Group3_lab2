package ua.edu.group3.laba2.model.implementetion.oracle;

import ua.edu.group3.laba2.model.interfaces.CustomerDAO;
import ua.edu.group3.laba2.model.pojo.Customer;

import javax.sql.RowSet;
import java.util.Collection;

/**
 * Created by user on 03.04.2017.
 */
public class OracleCustomerDAO implements CustomerDAO {

    public int insertCustomer() {
        return 0;
    }

    public boolean deleteCustomer(int id) {
        return false;
    }

    public Customer findCustomer(int id) {
        return null;
    }

    public boolean updateCustomer(int id) {
        return false;
    }

    public RowSet selectCustomersRS() {
        return null;
    }

    public Collection selectCustomersTO() {
        return null;
    }
}
