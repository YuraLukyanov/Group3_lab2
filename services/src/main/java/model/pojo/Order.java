package model.pojo;


import model.implementetion.services.util.ProductAndAmount;

import java.util.Collection;

public class Order {
    private Collection <ProductAndAmount> productsAndAmounts;
    private Customer customer;
    private int summ;

    public Order() {
    }

    public Order(Collection<ProductAndAmount> productsAndAmounts, Customer customer) {
        this.productsAndAmounts = productsAndAmounts;
        this.customer = customer;
        this.summ = calculateSumm();
    }

    public Collection<ProductAndAmount> getProductsAndAmounts() {
        return productsAndAmounts;
    }

    public void setProductsAndAmounts(Collection<ProductAndAmount> productsAndAmounts) {
        this.productsAndAmounts = productsAndAmounts;
        calculateSumm();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public int getSumm() {
        return summ;
    }

    private int calculateSumm(){
        int summ = 0;

        for (ProductAndAmount productAndAmount : productsAndAmounts) {
            summ += productAndAmount.getProduct().getPrice() * productAndAmount.getAmount();
        }

        return summ;
    }
}
