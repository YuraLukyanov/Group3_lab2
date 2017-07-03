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
        customerManager = null;
    }

    @Test
    public void addAndGet() throws Exception {

        Customer customer1 = new Customer("User1", "Login1", "1234");
        Customer customer2 = new Customer("User2", "Login2", "2345");
        Customer customer3 = new Customer("User3", "Login3", "3456");
        Customer customer4 = new Customer("User4", "Login4", "4567");
        Customer customer5 = new Customer("User5", "Login5", "5678");

        int id0, id1, id2, id3, id4;

        id0 = customerManager.add("User1", "Login1", "1234");
        id1 = customerManager.add("User2", "Login2", "2345");
        id2 = customerManager.add("User3", "Login3", "3456");
        id3 = customerManager.add("User4", "Login4", "4567");
        id4 = customerManager.add("User5", "Login5", "5678");

        Collection<Customer> temp = new ArrayList<>();

        temp.add(customer1);
        temp.add(customer2);
        temp.add(customer3);
        temp.add(customer4);
        temp.add(customer5);

        Util.assertEquals(customerManager.getAll(), temp);

        Assert.assertEquals(customerManager.get(id0), customer1);

        Assert.assertEquals(customerManager.get(id2), customer3);

        temp.clear();
        temp.add(customer4);
        Assert.assertEquals(customerManager.getByName("User4"), temp);

        temp.clear();
        temp.add(customer4);
        temp.add(customer5);
        Customer filter = new Customer();
        filter.setName("User");

        temp.clear();
        temp.add(customer1);
        temp.add(customer2);
        temp.add(customer3);
        temp.add(customer4);
        temp.add(customer5);
        Util.assertEquals(customerManager.getAll(), temp);
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

        Collection<Customer> temp = new ArrayList<>();

        customerManager.add("User1", "login1", "12345");
        int id1 = customerManager.add("User2", "login2", "12345");
        int id2 = customerManager.add("User3", "login3", "12345");

        temp.add(customer1);
        temp.add(customer2);
        customerManager.delete(id1);
        Util.assertEquals(customerManager.getAll(), temp);

        temp.clear();
        temp.add(customer1);
        customerManager.delete(id2);
        Assert.assertEquals(customerManager.getAll(), temp);
    }
}
