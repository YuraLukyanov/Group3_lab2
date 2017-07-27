package model.interfaces.services;

import model.pojo.Customer;

public interface IAuthorization {
    Customer getCustomer() throws Exception;
    void setCustomerManager(ICustomerManager customerManager);
}
