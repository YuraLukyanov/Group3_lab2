package model.interfaces.services;


import model.pojo.Customer;

public interface IAuthorization {
    Customer getCustomer();
    void setCustomerManager(ICustomerManager customerManager);
}
