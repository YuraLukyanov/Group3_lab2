package ua.edu.group3.laba2.model.implementetion.services;

import ua.edu.group3.laba2.model.interfaces.services.IProductManager;
import ua.edu.group3.laba2.model.pojo.Product;

import java.util.Collection;

public class ProductManager implements IProductManager{
    public Product get(int id) {
        return null;
    }

    public Collection<Product> getByName(String name) {
        return null;
    }

    public Collection<Product> getByColor(String color) {
        return null;
    }

    public Collection<Product> getByVolume(int volume) {
        return null;
    }

    public Collection<Product> getByWeight(int weight) {
        return null;
    }

    public Collection<Product> getByPrice(int price) {
        return null;
    }

    public Collection<Product> getAll() {
        return null;
    }

    public boolean update(int id, Product newProduct) {
        return false;
    }

    public int add(Product product) {
        return 0;
    }

    public boolean delete(int id) {
        return false;
    }
}
