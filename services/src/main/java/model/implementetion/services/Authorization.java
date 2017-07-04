package model.implementetion.services;

import model.interfaces.services.IAuthorization;
import model.interfaces.services.ICustomerManager;
import model.interfaces.services.IProductManager;
import model.pojo.Customer;

public class Authorization implements IAuthorization {
    private Customer customer;
    private ICustomerManager customerManager;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomerManager(ICustomerManager customerManager) throws Exception {
        this.customerManager = customerManager;
        customer = customerManager.getByLogin("admin");
    }
}
