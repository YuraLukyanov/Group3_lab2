package model.implementetion.services;

import model.interfaces.services.IAuthorization;
import model.interfaces.services.ICustomerManager;
import model.interfaces.services.IProductManager;
import model.pojo.Customer;

public class Authorization implements IAuthorization {
    private Customer customer;

    public Authorization(ICustomerManager customerManager) throws Exception {
        customer = customerManager.getByLogin("admin");
    }

    public Customer getCustomer() {
        return customer;
    }
}
