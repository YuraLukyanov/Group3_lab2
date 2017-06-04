package model.implementetion.services;

import model.interfaces.services.IAuthorization;
import model.pojo.Customer;

public class Authorization implements IAuthorization {
    private Customer customer;

    public Authorization(){
        customer = new Customer(1, "admin", "admin", "1234");
    }

    public Customer getCustomer() {
        return customer;
    }
}
