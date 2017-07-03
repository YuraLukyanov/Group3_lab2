package model.pojo;

import model.implementetion.services.util.Helper;
import model.implementetion.services.util.ProductAndAmount;

import java.util.Collection;

public class Order {
    private int id;

    private Collection<ProductAndAmount> productsAndAmounts;
    private Customer customer;
    private int summ;

    public Order() {
    }

    public Order(Collection<ProductAndAmount> productsAndAmounts, Customer customer) {
        this.productsAndAmounts = productsAndAmounts;
        this.customer = customer;
        this.summ = calculateSumm();
    }

    public Order(int id, Collection<ProductAndAmount> productsAndAmounts, Customer customer, int summ) {
        this.id = id;
        this.productsAndAmounts = productsAndAmounts;
        this.customer = customer;
        this.summ = summ;
    }

    public Collection<ProductAndAmount> getProductsAndAmounts() {
        return productsAndAmounts;
    }

    public void setProductsAndAmounts(Collection<ProductAndAmount> productsAndAmounts) {
        this.productsAndAmounts = productsAndAmounts;
        calculateSumm();
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }


    public void setSumm(int summ) {
        this.summ = summ;
    }

    public int getId() {
        return id;
    }

    private int calculateSumm() {
        int summ = 0;

        for (ProductAndAmount productAndAmount : productsAndAmounts) {
            summ += productAndAmount.getProduct().getPrice() * productAndAmount.getAmount();
        }

        return summ;
    }

    public int getSumm() {
        return summ;

    }

    @Override
    public String toString() {
        String toString = "Order: \n" + "\t productsAndAmounts: \n";

        for (ProductAndAmount productAndAmount : productsAndAmounts) {
            toString += "\t \t" + productAndAmount.toString() + "\n";
        }

        toString += "\t customer = " + customer.toString() + "\n \t summ = " + summ;

        return toString;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (summ != order.summ) return false;
        if (!Helper.isWithSameElements(productsAndAmounts, order.productsAndAmounts)) return false;
        return customer.equals(order.customer);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + productsAndAmounts.hashCode();
        result = 31 * result + customer.hashCode();
        result = 31 * result + summ;
        return result;
    }
}