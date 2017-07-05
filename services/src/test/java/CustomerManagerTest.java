import model.implementetion.services.CustomerManager;
import model.interfaces.services.ICustomerManager;
import model.pojo.Customer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;

public class CustomerManagerTest {
    private ICustomerManager customerManager;

    @Before
    public void init() throws Exception {
        customerManager = new CustomerManager();
        customerManager.deleteAll();
    }

    @After
    public void tearDown() throws Exception {
        customerManager.add("admin", "admin", "123456");
    }

    @Test
    public void addAndGet() throws Exception {

        Customer customer1 = new Customer("User1", "Login1", "1234");
        Customer customer2 = new Customer("User2", "Login2", "2345");
        Customer customer3 = new Customer("User3", "Login3", "3456");
        Customer customer4 = new Customer("User4", "Login4", "4567");
        Customer customer5 = new Customer("User5", "Login5", "5678");

        int id1, id3;

        id1 = customerManager.add("User1", "Login1", "1234");
        customerManager.add("User2", "Login2", "2345");
        id3 = customerManager.add("User3", "Login3", "3456");
        customerManager.add("User4", "Login4", "4567");
        customerManager.add("User5", "Login5", "5678");

        Collection<Customer> expected = new ArrayList<>();

        expected.add(customer1);
        expected.add(customer2);
        expected.add(customer3);
        expected.add(customer4);
        expected.add(customer5);

        UtilAssert.assertEquals(expected, customerManager.getAll());

        Assert.assertEquals(customer1, customerManager.get(id1));

        Assert.assertEquals(customer3, customerManager.get(id3));

        expected.clear();
        expected.add(customer4);
        Assert.assertEquals(expected, customerManager.getByName("User4"));

        expected.clear();
        expected.add(customer4);
        expected.add(customer5);
        Customer filter = new Customer();
        filter.setName("User");

        expected.clear();
        expected.add(customer1);
        expected.add(customer2);
        expected.add(customer3);
        expected.add(customer4);
        expected.add(customer5);
        UtilAssert.assertEquals(expected, customerManager.getAll());
    }

    @Test
    public void update() throws Exception {
        Customer newCustomer = new Customer("New customer", "customerX", "789999");

        int id = customerManager.add("Old customer", "customerX", "1234");
        customerManager.update(id, "New customer", "customerX", "789999");
        Assert.assertEquals(newCustomer, customerManager.get(id));
    }

    @Test
    public void delete() throws Exception {
        Customer customer1 = new Customer("User1", "login1", "12345");
        Customer customer2 = new Customer("User3", "login3", "12345");

        Collection<Customer> expected = new ArrayList<>();

        customerManager.add("User1", "login1", "12345");
        int id1 = customerManager.add("User2", "login2", "12345");
        int id2 = customerManager.add("User3", "login3", "12345");

        expected.add(customer1);
        expected.add(customer2);
        customerManager.delete(id1);
        UtilAssert.assertEquals(expected, customerManager.getAll());

        expected.clear();
        expected.add(customer1);
        customerManager.delete(id2);
        Assert.assertEquals(expected, customerManager.getAll());
    }
}
