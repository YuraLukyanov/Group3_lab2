package ua.edu.group3.laba2.model.implementetion.services.util;


import ua.edu.group3.laba2.model.pojo.Product;

public class ProductAndAmount {
    private Product product;
    private int amount;

    public ProductAndAmount(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public Product getProduct() {
        return product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}