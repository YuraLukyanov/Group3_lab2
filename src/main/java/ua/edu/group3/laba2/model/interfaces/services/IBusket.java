package ua.edu.group3.laba2.model.interfaces.services;

import ua.edu.group3.laba2.model.implementetion.services.util.ProductAndAmount;
import ua.edu.group3.laba2.model.pojo.Customer;
import ua.edu.group3.laba2.model.pojo.Order;
import ua.edu.group3.laba2.model.pojo.Product;

import java.util.Collection;

public interface IBusket {
    int add(Product product);
    boolean delete(int index);
    void setAmount(int index, int newAmount);
    Order getOrder(Customer customer);
    Collection<ProductAndAmount> getProductsAndAmounts();
}
