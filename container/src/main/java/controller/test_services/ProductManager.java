package controller.test_services;

import model.interfaces.services.IProductManager;
import model.pojo.Product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Nikolion on 18.05.2017.
 */
public class ProductManager implements IProductManager {
    @Override
    public Product get(int id) throws Exception {
        return new Product("Some product with id="+id,"green",100,200,300);
    }

    @Override
    public Collection<Product> getByName(String name) throws Exception {
        List<Product> list = new ArrayList<Product>();
        list.add(new Product(name, "red",1, 2,3));
        list.add(new Product(name, "red",2, 3,4));
        return list;
    }

    @Override
    public Collection<Product> getByColor(String color) throws Exception {
        List<Product> list = new ArrayList<Product>();
        list.add(new Product("some product1", color,1, 2,3));
        list.add(new Product("some product2", color,2, 3,4));
        return list;
    }

    @Override
    public Collection<Product> getByVolume(int volume) throws Exception {
        List<Product> list = new ArrayList<Product>();
        list.add(new Product("some product1", "green",volume, 2,3));
        list.add(new Product("some product2", "black",volume, 3,4));
        return list;
    }

    @Override
    public Collection<Product> getByWeight(int weight) throws Exception {
        List<Product> list = new ArrayList<Product>();
        list.add(new Product("some product1", "green",1, weight,3));
        list.add(new Product("some product2", "black",2, weight,4));
        return list;
    }

    @Override
    public Collection<Product> getByPrice(int price) throws Exception {
        List<Product> list = new ArrayList<Product>();
        list.add(new Product("some product1", "green",1, 2,price));
        list.add(new Product("some product2", "black",2, 3,price));
        return list;
    }

    @Override
    public Collection<Product> getAll() throws Exception {
        List<Product> list = new ArrayList<Product>();
        list.add(new Product("some product1", "green",1, 2,3));
        list.add(new Product("some product2", "black",2, 2,4));
        list.add(new Product("some product3", "black",2, 2,4));
        list.add(new Product("some product4", "red",2, 5,100));
        list.add(new Product("some product5", "black",2, 2,20));
        list.add(new Product("some product6", "black",2, 2,50));
        return list;
    }

    @Override
    public boolean update(int id, String name, String color, int weight, int volume, int price) throws Exception {
        return false;
    }

    @Override
    public int add(String name, String color, int weight, int volume, int price) throws Exception {
        return 0;
    }

    @Override
    public boolean delete(int id) throws Exception {
        return true;
    }
}
