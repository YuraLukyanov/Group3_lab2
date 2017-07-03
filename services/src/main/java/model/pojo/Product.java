package model.pojo;

public class Product {

    private int id;
    private String name;
    private String color;
    private int volume;
    private int weight;
    private int price;

    public Product() {
        this.volume = -1;
        this.weight = -1;
        this.price = -1;
        id = -1;   //to see when this field wasn't initialize
    }

    public Product(String name, String color, int weight, int volume, int price) {
        this.name = name;
        this.color = color;
        this.volume = volume;
        this.weight = weight;
        this.price = price;
        id = -1;   //to see when this field wasn't initialize
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

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (volume != product.volume) return false;
        if (weight != product.weight) return false;
        if (price != product.price) return false;
        if (name != null ? !name.equals(product.name) : product.name != null) return false;
        return color != null ? color.equals(product.color) : product.color == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (color != null ? color.hashCode() : 0);
        result = 31 * result + volume;
        result = 31 * result + weight;
        result = 31 * result + price;
        return result;
    }

}