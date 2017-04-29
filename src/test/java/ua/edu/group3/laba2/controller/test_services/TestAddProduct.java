package ua.edu.group3.laba2.controller.test_services;

/**
 * Created by Nikolion on 16.04.2017.
 */
public class TestAddProduct {
    Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestAddProduct that = (TestAddProduct) o;

        return product != null ? product.equals(that.product) : that.product == null;

    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
