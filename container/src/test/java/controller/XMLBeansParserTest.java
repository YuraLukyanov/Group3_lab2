package controller;

import controller.test_services.Basket;
import controller.test_services.Product;
import controller.test_services.TestAddProduct;
import org.junit.Before;
import org.junit.Test;


import java.io.File;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class XMLBeansParserTest {

    XMLBeansParser dOMXmlBeanParser = null;
    XMLBeansParser saxXMLParser = null;
    Product testProduct1 = new Product();
    Product testProduct2 = new Product();
    TestAddProduct addProduct = new TestAddProduct();
    Basket basket = new Basket();

    {
        testProduct2.setId(555);
        testProduct2.setType("computer");
        addProduct.setProduct(testProduct1);
        basket.setAddProduct(addProduct);
        basket.setPosition(2);
    }

    @Before
    public void CreateXMLBeanParseTest() throws Exception {
        String path = getClass().getClassLoader().getResource("test_beans.xml").getPath();
        dOMXmlBeanParser = new DOMXMLBeansParser();
        dOMXmlBeanParser.setFile(new File(path));
        saxXMLParser = new SAXXMLBeansParser();
        saxXMLParser.setFile(new File(path));
    }


    @Test
    public void parseBeansSAX() throws Exception {
        assertTrue("Beans not parsed", saxXMLParser.parseBeans());
    }
    @Test
    public void parseBeansDOM() throws Exception {
        assertTrue("Beans not parsed", saxXMLParser.parseBeans());
    }
    @Test
    public void getMapWithBeansSAX() throws Exception {
        saxXMLParser.parseBeans();
        Map beans = saxXMLParser.getBeans();

        assertTrue(saxXMLParser.getBeans() != null);
        assertTrue(beans.get("someProduct").equals(testProduct1));
        assertTrue(beans.get("someProduct1").equals(testProduct2));
        assertTrue(beans.get("testAddProduct").equals(addProduct));
        assertTrue(beans.get("basket").equals(basket));

    }

    @Test
    public void getMapWithBeansDOM() throws Exception {
        dOMXmlBeanParser.parseBeans();
        Map beans = dOMXmlBeanParser.getBeans();

        assertTrue(dOMXmlBeanParser.getBeans() != null);
        assertTrue(beans.get("someProduct").equals(testProduct1));
        assertTrue(beans.get("someProduct1").equals(testProduct2));
        assertTrue(beans.get("testAddProduct").equals(addProduct));
        assertTrue(beans.get("basket").equals(basket));

    }
}
