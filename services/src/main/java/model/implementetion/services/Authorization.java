package model.implementetion.services;

import model.interfaces.services.IAuthorization;
import model.interfaces.services.ICustomerManager;
import model.pojo.Customer;

public class Authorization implements IAuthorization {
    private Customer customer;
    private ICustomerManager customerManager;

    public Customer getCustomer() throws Exception {
        if (customer == null) customer = customerManager.getByLogin("admin");
        return customer;
    }

    public void setCustomerManager(ICustomerManager customerManager){
        this.customerManager = customerManager;
    }
}
