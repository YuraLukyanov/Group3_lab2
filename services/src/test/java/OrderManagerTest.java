import model.implementetion.services.OrderManager;
import model.implementetion.services.util.ProductAndAmount;
import model.interfaces.services.IOrderManager;
import model.pojo.Customer;
import model.pojo.Order;
import model.pojo.Product;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class OrderManagerTest {
    private IOrderManager orderManager;

    @Before
    public void init() {
        orderManager = new OrderManager();
    }

    @After
    public void tearDown() {
        orderManager = null;
    }

    @Test
    public void addAndGet() {
        Customer customer1 = new Customer("user1", "Login1", "123456");
        Customer customer2 = new Customer("user2", "Login2", "3456");
        Customer customer3 = new Customer("user3", "Login3", "32675");

        Product product1 = new Product("Product1", "red", 100, 100, 100);
        Product product2 = new Product("Product2", "white", 200, 200, 200);
        Product product3 = new Product("Product3", "black", 300, 300, 300);

        ProductAndAmount productAndAmount1 = new ProductAndAmount(product1);
        ProductAndAmount productAndAmount2 = new ProductAndAmount(product2);
        ProductAndAmount productAndAmount3 = new ProductAndAmount(product3);
        ProductAndAmount productAndAmount4 = new ProductAndAmount(product3);
        productAndAmount4.setAmount(2);
        ProductAndAmount productAndAmount5 = new ProductAndAmount(product3);
        productAndAmount5.setAmount(3);

        Collection<ProductAndAmount> productAndAmounts1 = new ArrayList<ProductAndAmount>();
        Collection<ProductAndAmount> productAndAmounts2 = new ArrayList<ProductAndAmount>();
        Collection<ProductAndAmount> productAndAmounts3 = new ArrayList<ProductAndAmount>();

        productAndAmounts1.add(productAndAmount1);
        productAndAmounts1.add(productAndAmount3);

        productAndAmounts2.add(productAndAmount2);
        productAndAmounts2.add(productAndAmount4);

        productAndAmounts3.add(productAndAmount5);

        Order order1 = new Order(productAndAmounts1, customer1);
        Order order2 = new Order(productAndAmounts2, customer2);
        Order order3 = new Order(productAndAmounts3, customer3);

        try {
            orderManager.add(order1);
            orderManager.add(order2);
            orderManager.add(order3);
        } catch (Exception exception) {
            System.out.println("Get an exception \n" + exception.toString());
        }

        Collection<Order> temp = new ArrayList<Order>();

        temp.add(order1);
        temp.add(order2);
        temp.add(order3);

        Assert.assertEquals(orderManager.getAll(), temp);

        Assert.assertEquals(orderManager.get(0), order1);

        Assert.assertEquals(orderManager.get(2), order3);

        temp.clear();
        temp.add(order1);
        temp.add(order2);
        temp.add(order3);
        Assert.assertEquals(orderManager.getAll(), temp);
    }

    @Test
    public void update() {
        Customer customer1 = new Customer("user1", "Login1", "123456");
        Customer customer2 = new Customer("user2", "Login2", "3456");

        Product product1 = new Product("Product1", "red", 100, 100, 100);
        Product product2 = new Product("Product2", "white", 200, 200, 200);
        Product product3 = new Product("Product3", "black", 300, 300, 300);

        ProductAndAmount productAndAmount1 = new ProductAndAmount(product1);
        ProductAndAmount productAndAmount2 = new ProductAndAmount(product2);
        ProductAndAmount productAndAmount3 = new ProductAndAmount(product3);
        ProductAndAmount productAndAmount4 = new ProductAndAmount(product3);
        productAndAmount4.setAmount(2);

        Collection<ProductAndAmount> productAndAmounts1 = new ArrayList<ProductAndAmount>();
        Collection<ProductAndAmount> productAndAmounts2 = new ArrayList<ProductAndAmount>();

        productAndAmounts1.add(productAndAmount1);
        productAndAmounts1.add(productAndAmount3);

        productAndAmounts2.add(productAndAmount2);
        productAndAmounts2.add(productAndAmount4);

        Order oldOrder = new Order(productAndAmounts1, customer1);
        Order newOrder = new Order(productAndAmounts2, customer2);

        try {
            int id = orderManager.add(oldOrder);
            orderManager.update(id, newOrder);
            Assert.assertEquals(orderManager.get(id), newOrder);

        } catch (Exception exception) {
            System.out.println("Get an exception \n" + exception.toString());
        }
    }

    @Test
    public void delete() {
        Customer customer1 = new Customer("user1", "Login1", "123456");
        Customer customer2 = new Customer("user2", "Login2", "3456");

        Product product1 = new Product("Product1", "red", 100, 100, 100);
        Product product2 = new Product("Product2", "white", 200, 200, 200);
        Product product3 = new Product("Product3", "black", 300, 300, 300);

        ProductAndAmount productAndAmount1 = new ProductAndAmount(product1);
        ProductAndAmount productAndAmount2 = new ProductAndAmount(product2);
        ProductAndAmount productAndAmount3 = new ProductAndAmount(product3);
        ProductAndAmount productAndAmount4 = new ProductAndAmount(product3);
        productAndAmount4.setAmount(2);

        Collection<ProductAndAmount> productAndAmounts1 = new ArrayList<ProductAndAmount>();
        Collection<ProductAndAmount> productAndAmounts2 = new ArrayList<ProductAndAmount>();

        productAndAmounts1.add(productAndAmount1);
        productAndAmounts1.add(productAndAmount3);

        productAndAmounts2.add(productAndAmount2);
        productAndAmounts2.add(productAndAmount4);

        Order order1 = new Order(productAndAmounts1, customer1);
        Order order2 = new Order(productAndAmounts2, customer2);

        Collection<Order> temp = new ArrayList<Order>();

        try {
            orderManager.add(order1);
            orderManager.add(order2);
        } catch (Exception exception) {
            System.out.println("Get an exception \n" + exception.toString());
        }

        temp.add(order1);
        orderManager.delete(1);
        Assert.assertEquals(orderManager.getAll(), temp);

        temp.clear();
        orderManager.delete(0);
        Assert.assertEquals(orderManager.getAll(), temp);
    }
}