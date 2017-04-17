package ua.edu.group3.laba2.model.interfaces.services;

import ua.edu.group3.laba2.model.pojo.Product;

import java.util.Collection;

public interface IProductManager {
    Product get(int id);
    Collection<Product> getByName(String name);
    Collection<Product> getByColor(String color);
    Collection<Product> getByVolume(int volume);
    Collection<Product> getByWeight(int weight);
    Collection<Product> getByPrice(int price);
    Collection<Product> getAll();
    boolean update(int id, Product newProduct);
    int add(Product product);
    boolean delete(int id);
}
