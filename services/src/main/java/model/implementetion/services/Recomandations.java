package model.implementetion.services;

import model.interfaces.services.IAuthorization;
import model.interfaces.services.IProductManager;
import model.interfaces.services.IRecomandations;
import model.pojo.Product;

import java.util.ArrayList;
import java.util.Collection;

public class Recomandations implements IRecomandations{
    private IAuthorization authorizitation;
    private IProductManager productManager;

    public void setAuthorizitation(IAuthorization authorizitation) {
        this.authorizitation = authorizitation;
    }

    public void setProductManager(IProductManager productManager) {
        this.productManager = productManager;
    }

    public Collection<Product> getProducts() {
        //TODO: get data from Facebook API
        //TODO: create filters from this data
        //TODO: get data from DB (using filters) through ProductManger service
        //TODO: return collection

        return new ArrayList<Product>();
    }
}
