import model.implementetion.services.*;
import model.implementetion.services.util.ProductAndAmount;
import model.interfaces.services.*;
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
    private IProductManager productManager;
    private IBusket busket;
    private IOrderManager orderManager;

    @Before
    public void init() throws Exception {
        productManager = new ProductManager();
        ICustomerManager customerManager = new CustomerManager();
        IAuthorization authorization = new Authorization(customerManager);

        busket = new Busket();
        busket.setAuthorization(authorization);
        busket.setProductManager(productManager);

        orderManager = new OrderManager();
        orderManager.setBusket(busket);
        orderManager.deleteAll();
    }

    @Test
    public void addAndGet() throws Exception {
        Customer customer = new Customer("admin", "admin", "123456");

        Product product1 = new Product("Product1", "red", 100, 100, 100);
        Product product2 = new Product("Product2", "white", 200, 200, 200);
        Product product3 = new Product("Product3", "black", 300, 300, 300);

        int idProduct1 = productManager.add("Product1", "red", 100, 100, 100);
        int idProduct2 = productManager.add("Product2", "white", 200, 200, 200);
        int idProduct3 = productManager.add("Product3", "black", 300, 300, 300);

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

        Order order1 = new Order(productAndAmounts1, customer);
        Order order2 = new Order(productAndAmounts2, customer);
        Order order3 = new Order(productAndAmounts3, customer);

        //adding first order
        busket.add(idProduct1);
        busket.add(idProduct3);
        int idOrder1 = orderManager.add();
        busket.clear();

        //adding second order
        busket.add(idProduct2);
        int prodAndAmountToChange = busket.add(idProduct3);
        busket.setAmount(prodAndAmountToChange, 2);
        orderManager.add();
        busket.clear();

        //adding third order
        prodAndAmountToChange = busket.add(idProduct3);
        busket.setAmount(prodAndAmountToChange, 3);
        int idOrder3 = orderManager.add();
        busket.clear();

        Collection<Order> temp = new ArrayList<>();

        temp.add(order1);
        temp.add(order2);
        temp.add(order3);

        Assert.assertEquals(temp, orderManager.getAll());

        Assert.assertEquals(order1, orderManager.get(idOrder1));

        Assert.assertEquals(order3, orderManager.get(idOrder3));

        temp.clear();
        temp.add(order1);
        temp.add(order2);
        temp.add(order3);
        Assert.assertEquals(orderManager.getAll(), temp);
    }

    @Test
    public void delete() throws Exception {
        IProductManager productManager = new ProductManager();
        Customer customer1 = new Customer("admin", "admin", "123456");

        Product product1 = new Product("Product1", "red", 100, 100, 100);
        Product product2 = new Product("Product2", "black", 300, 300, 300);
        int idProduct1 = productManager.add("Product1", "red", 100, 100, 100);
        int idProduct2 = productManager.add("Product2", "black", 300, 300, 300);

        ProductAndAmount productAndAmount1 = new ProductAndAmount(product1);
        ProductAndAmount productAndAmount3 = new ProductAndAmount(product2);

        Collection<ProductAndAmount> productAndAmounts1 = new ArrayList<>();

        productAndAmounts1.add(productAndAmount1);
        productAndAmounts1.add(productAndAmount3);
        Order order1 = new Order(productAndAmounts1, customer1);

        Collection<Order> temp = new ArrayList<>();

        int idOrder1;
        int idOrder2;

        idOrder1 = orderManager.add();
        busket.clear();
        busket.add(idProduct1);
        busket.add(idProduct2);
        idOrder2 = orderManager.add();

        temp.add(order1);
        orderManager.delete(idOrder1);
        Assert.assertEquals(orderManager.getAll(), temp);

        temp.clear();
        orderManager.delete(idOrder2);
        Assert.assertEquals(orderManager.getAll(), temp);
    }
}