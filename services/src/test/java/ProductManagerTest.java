import model.implementetion.services.ProductManager;
import model.interfaces.services.IProductManager;
import model.pojo.Product;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class ProductManagerTest {
    private IProductManager productManager;

    @Before
    public void init() throws Exception {
        productManager = new ProductManager();
        productManager.deleteAll();
    }

    @Test
    public void addAndGet() throws Exception {
        Product product1 = new Product("Cup", "red", 100, 300, 10);
        Product product2 = new Product("Mug", "blue", 100, 300, 10);
        Product product3 = new Product("Beautiful cup", "black", 100, 300, 30);
        Product product4 = new Product("Big cup", "yellow", 200, 500, 20);
        Product product5 = new Product("Small cup", "white", 50, 100, 5);


        int id1 = productManager.add("Cup", "red", 100, 300, 10);
        productManager.add("Mug", "blue", 100, 300, 10);
        int id3 = productManager.add("Beautiful cup", "black", 100, 300, 30);
        productManager.add("Big cup", "yellow", 200, 500, 20);
        productManager.add("Small cup", "white", 50, 100, 5);

        Collection<Product> expected = new ArrayList<>();
        
        expected.add(product1);
        expected.add(product2);
        expected.add(product3);
        expected.add(product4);
        expected.add(product5);

        Util.assertEquals(expected, productManager.getAll());

        Product product = productManager.get(id1);

        Assert.assertEquals(product1, product);

        Assert.assertEquals(product3, productManager.get(id3));

        expected.clear();
        expected.add(product2);
        Util.assertEquals(expected, productManager.getByName("Mug"));
        
        expected.clear();
        expected.add(product1);
        Util.assertEquals(expected, productManager.getByColor("red"));

        expected.clear();
        expected.add(product1);
        expected.add(product2);
        expected.add(product3);
        Util.assertEquals(expected, productManager.getByWeight(100));


        expected.clear();
        expected.add(product4);
        Util.assertEquals(expected, productManager.getByVolume(500));


        expected.clear();
        expected.add(product5);
        Util.assertEquals(expected, productManager.getByPrice(5));

        expected.clear();
        expected.add(product1);
        expected.add(product2);
        expected.add(product3);
        expected.add(product4);
        expected.add(product5);
        Util.assertEquals(expected, productManager.getAll());
    }

    @Test
    public void update() {
        Product newProduct = new Product("New product", "red", 400, 500, 600);

        try {
            int id = productManager.add("Old product", "black", 100, 200, 300);
            productManager.update(id, "New product", "red", 400, 500, 600);
            Assert.assertEquals(productManager.get(id), newProduct);

        } catch (Exception exception) {
            System.out.println("Get an exception \n" + exception.toString());
        }
    }

    @Test
    public void delete() throws Exception {
        Product product1 = new Product("one", "sdfsdf", 10, 10, 10);
        Product product3 = new Product("three", "sdfsdf", 10, 10, 10);

        Collection<Product> expected = new ArrayList<>();

        int id0 = productManager.add("one", "sdfsdf", 10, 10, 10);
        int id1 = productManager.add("two", "sdfsdf", 10, 10, 10);
        int id2 = productManager.add("three", "sdfsdf", 10, 10, 10);

        expected.add(product1);
        expected.add(product3);
        productManager.delete(id1);
        Util.assertEquals(expected, productManager.getAll());

        expected.clear();
        expected.add(product1);
        productManager.delete(id2);
        Util.assertEquals(expected, productManager.getAll());
    }


}
