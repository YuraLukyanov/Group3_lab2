package model.implementetion.oracle;

import model.interfaces.dao.CustomerDAO;
import model.pojo.Customer;

import java.util.Collection;

public class OracleCustomerDAO implements CustomerDAO {

    public int insert(Customer customer){
        return 0;
    }

    public boolean delete(int id) {
        return false;
    }

    public Customer find(int id) {
        return null;
    }

    public boolean update(int id, Customer newCustomer) {
        return false;
    }

    public Collection<Customer> selectTO(Customer filterCustomer) {
        return null;
    }

}
