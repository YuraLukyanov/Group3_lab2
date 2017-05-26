import model.implementetion.services.ProductManager;
import model.interfaces.services.IProductManager;
import model.pojo.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class ProductManagerTest {
    private IProductManager productManager;

    @Before
    public void init() {
        productManager = new ProductManager();
    }

    @After
    public void tearDown() {
        productManager = null;
    }

    @Test
    public void addAndGet() throws Exception {
        Product product1 = new Product("Cup", "red", 100, 300, 10);
        Product product2 = new Product("Mug", "blue", 100, 300, 10);
        Product product3 = new Product("Beautiful cup", "black", 100, 300, 30);
        Product product4 = new Product("Big cup", "yellow", 200, 500, 20);
        Product product5 = new Product("Small cup", "white", 50, 100, 5);


        int id0 = productManager.add("Cup", "red", 100, 300, 10);
        int id1 = productManager.add("Mug", "blue", 100, 300, 10);
        int id2 = productManager.add("Beautiful cup", "black", 100, 300, 30);
        int id3 = productManager.add("Big cup", "yellow", 200, 500, 20);
        int id4 = productManager.add("Small cup", "white", 50, 100, 5);

        Collection<Product> temp = new ArrayList<>();

        temp.add(product1);
        temp.add(product2);
        temp.add(product3);
        temp.add(product4);
        temp.add(product5);

        Assert.assertEquals(productManager.getAll(), temp);

        Assert.assertEquals(productManager.get(id0), product1);

        Assert.assertEquals(productManager.get(id2), product3);

        temp.clear();
        temp.add(product3);
        Assert.assertEquals(productManager.getByName("Mug"), temp);

        temp.clear();
        temp.add(product1);
        Assert.assertEquals(productManager.getByColor("red"), temp);

        temp.clear();
        temp.add(product1);
        temp.add(product2);
        temp.add(product3);
        Assert.assertEquals(productManager.getByWeight(100), temp);


        temp.clear();
        temp.add(product4);
        Assert.assertEquals(productManager.getByVolume(500), temp);


        temp.clear();
        temp.add(product5);
        Assert.assertEquals(productManager.getByPrice(5), temp);

        temp.clear();
        temp.add(product1);
        temp.add(product2);
        temp.add(product3);
        temp.add(product4);
        temp.add(product5);
        Assert.assertEquals(productManager.getAll(), temp);
    }

    @Test
    public void update() {
        Product newProduct = new Product("New product", "red", 200, 200, 200);

        try {
            int id = productManager.add("Old product", "black", 100, 100, 100);
            productManager.update(id, "New product", "red", 200, 200, 200);
            Assert.assertEquals(productManager.get(id), newProduct);

        } catch (Exception exception) {
            System.out.println("Get an exception \n" + exception.toString());
        }
    }

    @Test
    public void delete() throws Exception {
        Product product1 = new Product("one", "sdfsdf", 10, 10, 10);
        Product product3 = new Product("three", "sdfsdf", 10, 10, 10);

        Collection<Product> temp = new ArrayList<>();

        int id0 = productManager.add("one", "sdfsdf", 10, 10, 10);
        int id1 = productManager.add("two", "sdfsdf", 10, 10, 10);
        int id2 = productManager.add("three", "sdfsdf", 10, 10, 10);

        temp.add(product1);
        temp.add(product3);
        productManager.delete(id1);
        Assert.assertEquals(productManager.getAll(), temp);

        temp.clear();
        temp.add(product1);
        productManager.delete(id2);
        Assert.assertEquals(productManager.getAll(), temp);
    }


}
