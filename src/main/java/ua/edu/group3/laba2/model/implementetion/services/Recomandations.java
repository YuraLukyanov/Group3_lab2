package ua.edu.group3.laba2.model.implementetion.services;

import ua.edu.group3.laba2.model.interfaces.services.IProductManager;
import ua.edu.group3.laba2.model.interfaces.services.IRecomandations;
import ua.edu.group3.laba2.model.pojo.Product;

import java.util.ArrayList;
import java.util.Collection;

public class Recomandations implements IRecomandations{
    private IProductManager productManager;

    public void setProductManager(IProductManager productManager) {
        this.productManager = productManager;
    }

    public Collection<Product> getProducts(int customerId) {
        //TODO: get data from Facebook API
        //TODO: create filters from this data
        //TODO: get data from DB (using filters) through ProductManger service
        //TODO: return collection

        return new ArrayList<Product>();
    }
}
