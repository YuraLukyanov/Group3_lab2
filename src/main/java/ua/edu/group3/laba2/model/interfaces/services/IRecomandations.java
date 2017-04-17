package ua.edu.group3.laba2.model.interfaces.services;

import ua.edu.group3.laba2.model.pojo.Product;

import java.util.Collection;

public interface IRecomandations {
    Collection <Product> getProducts(int customerId);
}
