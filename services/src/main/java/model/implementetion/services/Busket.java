package model.implementetion.services;

import model.implementetion.services.util.ProductAndAmount;
import model.interfaces.services.IAuthorization;
import model.interfaces.services.IBusket;
import model.pojo.Order;
import model.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.LinkedHashSet;

public class Busket implements IBusket {
    private static final Logger LOGGER = LoggerFactory.getLogger(Busket.class);

    private LinkedHashSet<ProductAndAmount> productsAndAmounts = new LinkedHashSet<ProductAndAmount>();
    private IAuthorization authorization;

    public void setAuthorization(IAuthorization authorization) {
        this.authorization = authorization;
    }

    public int add(Product product) throws Exception {
        ProductAndAmount productAndAmount = new ProductAndAmount(product);

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
        int i = 0;
        for (ProductAndAmount productAndAmount : productsAndAmounts) {
            if (i == index) {
                return productAndAmount;
            }
            i++;
        }
        return null;
    }
}
