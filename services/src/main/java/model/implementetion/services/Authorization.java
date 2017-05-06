package model.implementetion.services;

import model.interfaces.services.IAuthorization;
import model.interfaces.services.ICustomerManager;
import model.pojo.Customer;

public class Authorization implements IAuthorization {
    private ICustomerManager customerManager;
    private Customer customer;

    public Authorization(ICustomerManager customerManager, String login, String password) {
        Customer filter = new Customer();
        filter.setLogin(login);
        filter.setPassword(password);
        for (Customer customer : customerManager.getByFilter(filter)) {
            this.customer = customer;
            return;
        }
    }

    public Customer getCustomer() {
        return customer;
    }

    public boolean isSuccessful() {
        return customer != null;
    }
}
