package model.pojo;

public class Product {
    private int id;
    private String name;
    private String color;
    private int volume;
    private int weight;
    private int price;

    public Product() {
    }

    public Product(String name, String color, int weight, int volume, int price) {
        this.name = name;
        this.color = color;
        this.volume = volume;
        this.weight = weight;
        this.price = price;
    }

    public Product(int id, String name, String color, int volume, int weight, int price) {
        this.id = id;
        this.name = name;
        this.color = color;
        this.volume = volume;
        this.weight = weight;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Product: " +
                "name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", volume=" + volume +
                ", weight=" + weight +
                ", price=" + price;
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

    public int getId() {
        return id;
    }
}