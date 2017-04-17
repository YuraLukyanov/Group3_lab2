package ua.edu.group3.laba2.model.pojo;

public class Product {
   private String name;
   private String color;
   private int volume;
   private int weight;
   private int price;

    public Product() {
    }

    public Product(String name, String color, int volume, int weight, int price) {
        this.name = name;
        this.color = color;
        this.volume = volume;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}