package ua.edu.group3.laba2.controller.implementation;

import org.junit.Before;
import org.junit.Test;
import ua.edu.group3.laba2.controller.interfaces.XMLBeansParser;
import ua.edu.group3.laba2.controller.test_services.Product;
import ua.edu.group3.laba2.controller.test_services.TestAddProduct;
import ua.edu.group3.laba2.model.services.AddProduct;

import static org.junit.Assert.*;

import java.io.File;
import java.util.Map;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class XMLBeansParserTest {

    XMLBeansParser dOMXmlBeanParser = null;
    Product testProduct1 = new Product();
    Product testProduct2 = new Product();
    TestAddProduct addProduct = new TestAddProduct();
    {
        testProduct2.setId(500);
        testProduct2.setType("computer");
        addProduct.setProduct(testProduct1);
    }

    @Before
    public void CreateXMLBeanParseTest() throws Exception {
        String path = getClass().getClassLoader().getResource("test_beans.xml").getPath();
        dOMXmlBeanParser = new DOMXMLBeansParser();
        dOMXmlBeanParser.setFile(new File(path));
    }

    @Test
    public void parseBeans() throws Exception {
        assertTrue("Beans not parsed", dOMXmlBeanParser.parseBeans());
    }

    @Test
    public void getMapWithBeans() throws Exception {
        dOMXmlBeanParser.parseBeans();
        Map beans = dOMXmlBeanParser.getBeans();

        assertTrue(dOMXmlBeanParser.getBeans() != null);
        assertTrue(beans.get("someProduct").equals(testProduct1));
        assertTrue(beans.get("someProduct1").equals(testProduct2));
        assertTrue(beans.get("testAddProduct").equals(addProduct));

    }
}
