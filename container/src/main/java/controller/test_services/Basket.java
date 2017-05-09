package controller.test_services;


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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Basket)) return false;

        Basket basket = (Basket) o;

        if (position != basket.getPosition()) return false;
        return addProduct != null ? addProduct.equals(basket.getAddProduct()) : basket
                .addProduct == null;

    }

    @Override
    public int hashCode() {
        int result = addProduct != null ? addProduct.hashCode() : 0;
        result = 31 * result + position;
        return result;
    }
}
