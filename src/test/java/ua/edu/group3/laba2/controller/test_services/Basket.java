package ua.edu.group3.laba2.controller.test_services;

/**
 * Created by Nikolion on 04.05.2017.
 */
public class Basket {
    private TestAddProduct addProduct;
    private int position;

    public Basket() {
    }

    public TestAddProduct getAddProduct() {
        return addProduct;
    }

    public void setAddProduct(TestAddProduct addProduct) {
        this.addProduct = addProduct;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "addProduct=" + addProduct +
                ", position=" + position +
                '}';
    }
}
