import model.implementetion.services.Authorization;
import model.implementetion.services.Busket;
import model.implementetion.services.OrderManager;
import model.implementetion.services.ProductManager;
import model.implementetion.services.util.ProductAndAmount;
import model.interfaces.services.IAuthorization;
import model.interfaces.services.IBusket;
import model.interfaces.services.IOrderManager;
import model.interfaces.services.IProductManager;
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
    private IBusket busket;
    private IAuthorization authorization;
    private IProductManager productManager;

    @Before
    public void init() {
        authorization = new Authorization();
        productManager = new ProductManager();
        busket = new Busket();
        busket.setAuthorization(authorization);
        busket.setProductManager(productManager);
        orderManager = new OrderManager();
        orderManager.setBusket(busket);
    }

    @After
    public void tearDown() {
        orderManager = null;
        busket = null;
        productManager = null;
        authorization = null;
    }

    @Test
    public void addAndGet() throws Exception {
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

        Collection<ProductAndAmount> productAndAmounts1 = new ArrayList<>();
        Collection<ProductAndAmount> productAndAmounts2 = new ArrayList<>();
        Collection<ProductAndAmount> productAndAmounts3 = new ArrayList<>();

        productAndAmounts1.add(productAndAmount1);
        productAndAmounts1.add(productAndAmount3);

        productAndAmounts2.add(productAndAmount2);
        productAndAmounts2.add(productAndAmount4);

        productAndAmounts3.add(productAndAmount5);

        Order order1 = new Order(productAndAmounts1, customer1);
        Order order2 = new Order(productAndAmounts2, customer2);
        Order order3 = new Order(productAndAmounts3, customer3);

        orderManager.add();

        Collection<Order> temp = new ArrayList<>();

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
    public void delete() throws Exception {
        Customer customer1 = new Customer("user1", "Login1", "123456");

        Product product1 = new Product("Product1", "red", 100, 100, 100);
        Product product3 = new Product("Product3", "black", 300, 300, 300);

        ProductAndAmount productAndAmount1 = new ProductAndAmount(product1);
        ProductAndAmount productAndAmount3 = new ProductAndAmount(product3);

        Collection<ProductAndAmount> productAndAmounts1 = new ArrayList<>();

        productAndAmounts1.add(productAndAmount1);
        productAndAmounts1.add(productAndAmount3);
        Order order1 = new Order(productAndAmounts1, customer1);

        Collection<Order> temp = new ArrayList<>();

        int indexOfJustAddedElement1;
        int indexOfJustAddedElement2;

        indexOfJustAddedElement1 = orderManager.add();
        busket.clear();
        busket.add(0);
        busket.add(1);
        indexOfJustAddedElement2 = orderManager.add();

        temp.add(order1);
        orderManager.delete(indexOfJustAddedElement1);
        Assert.assertEquals(orderManager.getAll(), temp);

        temp.clear();
        orderManager.delete(indexOfJustAddedElement2);
        Assert.assertEquals(orderManager.getAll(), temp);
    }
}