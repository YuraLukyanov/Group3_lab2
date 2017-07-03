package model.interfaces.services;

import model.pojo.Product;

import java.util.Collection;

public interface IProductManager {
    Product get(int id) throws Exception;
    Collection<Product> getByName(String name) throws Exception;
    Collection<Product> getByColor(String color) throws Exception;
    Collection<Product> getByVolume(int volume) throws Exception;
    Collection<Product> getByWeight(int weight) throws Exception;
    Collection<Product> getByPrice(int price) throws Exception;
    Collection<Product> getAll() throws Exception;
    boolean update(int id, String name, String color, int weight, int volume, int price) throws Exception;
    int add(String name, String color, int weight, int volume, int price) throws Exception;
    boolean delete(int id) throws Exception;
    boolean deleteAll() throws Exception;
}
