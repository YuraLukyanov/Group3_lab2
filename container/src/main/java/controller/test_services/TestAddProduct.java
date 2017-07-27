package controller.test_services;


public class TestAddProduct {
    private Product product;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

       TestAddProduct that = (TestAddProduct) o;

        return product != null ? product.equals(that.getProduct()) : that.getProduct() ==
                null;

    }

    @Override
    public int hashCode() {
        return product != null ? product.hashCode() : 0;
    }

    @SuppressWarnings("WeakerAccess")
    protected Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "TestAddProduct{" +
                "product=" + product +
                '}';
    }
}
