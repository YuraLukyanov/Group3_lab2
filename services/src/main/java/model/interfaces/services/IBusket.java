package model.interfaces.services;

import model.implementetion.services.util.ProductAndAmount;
import model.pojo.Order;

import java.util.Collection;

public interface IBusket {
    int add(int id) throws Exception;
    boolean delete(int index);
    boolean setAmount(int index, int newAmount);
    Order getOrder() throws Exception;
    Collection<ProductAndAmount> getProductsAndAmounts();
    boolean clear();

    void setAuthorization(IAuthorization authorization);

    void setProductManager(IProductManager productManager);
}
