package model.implementetion.services;

import com.google.common.collect.Iterables;
import model.implementetion.services.util.ProductAndAmount;
import model.interfaces.services.IAuthorization;
import model.interfaces.services.IBusket;
import model.interfaces.services.IProductManager;
import model.pojo.Order;
import model.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Busket implements IBusket {
    private static final Logger LOGGER = LoggerFactory.getLogger(Busket.class);

    private Collection<ProductAndAmount> productsAndAmounts = new LinkedHashSet<>();
    private IAuthorization authorization;
    private IProductManager productManager;

    public void setAuthorization(IAuthorization authorization) {
        this.authorization = authorization;
    }

    public int add(int id) throws Exception {
        Product product = productManager.get(id);

        if (productsAndAmounts.add(new ProductAndAmount(product))) {
            return productsAndAmounts.size() - 1; //return index of recently added element
        } else {
            LOGGER.error("Can't add - " + product.toString() + "to busket.");
            throw new Exception("Can't add this product to busket.");
        }
    }

    public boolean delete(int index) {
        if (index > productsAndAmounts.size() - 1) {
            return false;
        }

        return productsAndAmounts.remove(getProductAndAmount(index));
    }

    public boolean setAmount(int index, int newAmount) {
        if (index > productsAndAmounts.size() - 1
                || index < 0
                || newAmount < 1) {

            return false;
        }

        //noinspection ConstantConditions
        getProductAndAmount(index).setAmount(newAmount);
        return true;
    }

    public Order getOrder() {
        return new Order(productsAndAmounts, authorization.getCustomer());
    }

    public Collection<ProductAndAmount> getProductsAndAmounts() {
        return productsAndAmounts;
    }

    private ProductAndAmount getProductAndAmount(int index) {
        return Iterables.get(productsAndAmounts, index);
    }

    public boolean clear(){
        productsAndAmounts.clear();
        return productsAndAmounts.isEmpty();
    }

    public void setProductManager(IProductManager productManager) {
        this.productManager = productManager;
    }
}
