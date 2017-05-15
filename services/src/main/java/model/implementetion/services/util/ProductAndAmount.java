package model.implementetion.services.util;


import model.pojo.Product;

public class ProductAndAmount {
    private Product product;

    @Override
    public String toString() {
        return "ProductAndAmount: " + product.toString() + "; Amount = " + amount + ".";
    }

    private int amount;

    public ProductAndAmount(Product product) {
        this.product = product;
        amount = 1;
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