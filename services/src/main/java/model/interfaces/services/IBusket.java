package model.interfaces.services;

import model.implementetion.services.util.ProductAndAmount;
import model.pojo.Order;
import model.pojo.Product;

import java.util.Collection;

public interface IBusket {
    int add(int id) throws Exception;
    boolean delete(int index);
    boolean setAmount(int index, int newAmount);
    Order getOrder();
    Collection<ProductAndAmount> getProductsAndAmounts();
    boolean clear();
}
