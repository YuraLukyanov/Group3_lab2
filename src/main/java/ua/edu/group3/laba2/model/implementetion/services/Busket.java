package ua.edu.group3.laba2.model.implementetion.services;

import ua.edu.group3.laba2.model.implementetion.services.util.ProductAndAmount;
import ua.edu.group3.laba2.model.interfaces.services.IBusket;
import ua.edu.group3.laba2.model.pojo.Customer;
import ua.edu.group3.laba2.model.pojo.Order;
import ua.edu.group3.laba2.model.pojo.Product;

import java.util.ArrayList;
import java.util.Collection;

public class Busket implements IBusket{
    private Collection <ProductAndAmount> productsAndAmounts = new ArrayList<ProductAndAmount>(); //TODO

    public int add(Product product) {
        return 0;
    }

    public boolean delete(int index) {
        return false;
    }

    public void setAmount(int index, int newAmount) {

    }

    public Order getOrder(Customer customer) {
        return null;
    }

    public Collection<ProductAndAmount> getProductsAndAmounts() {
        return productsAndAmounts;
    }
}
