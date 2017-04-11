package ua.edu.group3.laba2.controller.implementation;

import org.junit.Before;
import org.junit.Test;
import ua.edu.group3.laba2.model.services.AddProduct;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import static org.junit.Assert.*;


/**
 * Created by Nikolion on 11.04.2017.
 */
public class ContainerTest {
    File goodFile1 = new File("");
    File badFile = new File("");
    @Before
    public void getInstanceOfContainer() throws Exception {
        assertNotNull("Container instance is NULL",Container.getInstance());
        assertTrue("Container is not instance of Container",
                Container.getInstance() instanceof Container);
    }

    @Before
    public void initContainer() throws Exception {
        //assertFalse("Container init from bad file",Container.getInstance().init(badFile));
        assertTrue("Container not init", Container.getInstance().init(goodFile1));

    }

    @Test
    public void getBeanByName() throws Exception {
        Object testAddProduct = Container.getInstance().getBean("addProduct");
        assertTrue(testAddProduct instanceof AddProduct);
    }

    @Test
    public void getBeanByNameAndClass() throws Exception {

        assertEquals("Classes not Equal",
                Container.getInstance().getBean("addProduct", AddProduct.class).getClass(),
                AddProduct.class);
    }

    @Test
    public void getBeanByClass() throws Exception {
        Collection testCollection = Container.getInstance().getBean(AddProduct.class);
        Collection tempCollection = new ArrayList();
        testCollection.add(new AddProduct());
        assertTrue("The quantity does not match",
                testCollection instanceof Collection
                && testCollection.size()==tempCollection.size());
    }

    @Test
    public void refreshContainerFromFile() throws Exception {
        assertTrue("Refresh is false",Container.getInstance().refresh());
    }

}