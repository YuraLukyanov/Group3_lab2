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
    public void init() {
        customerManager = new CustomerManager();
    }

    @After
    public void tearDown() {
        customerManager = null;
    }

    @Test
    public void addAndGet() {
        Customer customer1 = new Customer("User1", "Login1", "1234");
        Customer customer2 = new Customer("User2", "Login2", "2345");
        Customer customer3 = new Customer("User3", "Login3", "3456");
        Customer customer4 = new Customer("User", "Login4", "4567");
        Customer customer5 = new Customer("User", "Login5", "5678");

        try {
            customerManager.add(customer1);
            customerManager.add(customer2);
            customerManager.add(customer3);
            customerManager.add(customer4);
            customerManager.add(customer5);
        } catch (Exception exception) {
            System.out.println("Get an exception \n" + exception.toString());
        }

        Collection<Customer> temp = new ArrayList<Customer>();

        temp.add(customer1);
        temp.add(customer2);
        temp.add(customer3);
        temp.add(customer4);
        temp.add(customer5);

        Assert.assertEquals(customerManager.getAll(), temp);

        Assert.assertEquals(customerManager.get(0), customer1);

        Assert.assertEquals(customerManager.get(2), customer3);

        temp.clear();
        temp.add(customer3);
        Assert.assertEquals(customerManager.getByName("User3"), temp);

        temp.clear();
        temp.add(customer4);
        temp.add(customer5);
        Customer filter = new Customer();
        filter.setName("User");
        Assert.assertEquals(customerManager.getByFilter(filter), temp);

        temp.clear();
        temp.add(customer2);
        Assert.assertEquals(customerManager.getByFilter(customer2), temp);

        temp.clear();
        temp.add(customer1);
        temp.add(customer2);
        temp.add(customer3);
        temp.add(customer4);
        temp.add(customer5);
        Assert.assertEquals(customerManager.getAll(), temp);
    }

    @Test
    public void update() {
        Customer oldCustomer = new Customer("Old customer", "user1", "1234");
        Customer newCustomer = new Customer("New customer", "user2", "5678");

        try {
            int id = customerManager.add(oldCustomer);
            customerManager.update(id, newCustomer);
            Assert.assertEquals(customerManager.get(id), newCustomer);

        } catch (Exception exception) {
            System.out.println("Get an exception \n" + exception.toString());
        }
    }

    @Test
    public void delete() {
        Customer customer1 = new Customer("User1", "login1", "12345");
        Customer customer2 = new Customer("User2", "login2", "12346");
        Customer customer3 = new Customer("User3", "login3", "12347");

        Collection<Customer> temp = new ArrayList<Customer>();

        try {
            customerManager.add(customer1);
            customerManager.add(customer2);
            customerManager.add(customer3);
        } catch (Exception exception) {
            System.out.println("Get an exception \n" + exception.toString());
        }

        temp.add(customer1);
        temp.add(customer3);
        customerManager.delete(1);
        Assert.assertEquals(customerManager.getAll(), temp);

        temp.clear();
        temp.add(customer1);
        customerManager.delete(2);
        Assert.assertEquals(customerManager.getAll(), temp);
    }
}
