package controller;

import controller.test_services.Basket;
import controller.test_services.Product;
import controller.test_services.TestAddProduct;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;


/**
 * Created by Nikolion on 11.04.2017.
 */
public class ContainerTest {
    @Before
    public void getInstanceOfContainer() throws Exception {
        assertNotNull("Container instance is NULL",Container.getInstance());
        assertTrue("Container is not instance of Container",
                Container.getInstance() instanceof Container);
    }

    @Before
    public void initContainer() throws Exception {
        String fileName = "test_beans.xml";
        assertFalse("Container init from bad file",Container.getInstance()
                .init(fileName+ ".xml"));
        assertTrue("Container not init", Container.getInstance().init(fileName));

    }

    @Test
    public void getBeanByName() throws Exception {
        TestAddProduct addProduct = new TestAddProduct();
        Product product = new Product();
        addProduct.setProduct(product);
        Object testAddProductFromContainer = Container.getInstance().getBean("testAddProduct");

        Product realProduct = new Product();
        realProduct.setId(555);
        realProduct.setType("computer");
        Object productFromContainer = Container.getInstance().getBean("someProduct1");

        Basket basket = new Basket();
        basket.setAddProduct(addProduct);
        basket.setPosition(2);
        Object basketFromContainer = Container.getInstance().getBean("basket");

        assertEquals("Products are not equal",realProduct,productFromContainer);
        assertEquals("AddProducts are not equal",addProduct,testAddProductFromContainer);
        assertEquals("Baskets are not equal",basket, basketFromContainer);
    }

    @Test
    public void getBeanByNameAndClass() throws Exception {
        TestAddProduct addProduct = new TestAddProduct();
        Class t = addProduct.getClass();
        assertEquals("Classes not Equal",
                Container.getInstance().getBean("testAddProduct",
                        TestAddProduct.class).getClass(),TestAddProduct.class );
    }

    @Test
    public void getBeanByClass() throws Exception {
        Collection collectionFromContainer = Container.getInstance().getBean(Product.class);
        assertTrue("The quantity does not match",
                collectionFromContainer instanceof Collection
                && collectionFromContainer.size()==2);

        for (Object m:collectionFromContainer) {
            assertTrue("Object not instance of Product",m instanceof Product);
        }

    }

    @Test
    public void refreshContainerFromFile() throws Exception {
        assertTrue("Refresh is false",Container.getInstance().refresh());
    }

}