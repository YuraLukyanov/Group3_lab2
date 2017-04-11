package ua.edu.group3.laba2.controller.implementation;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.util.Map;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class XMLBeansParserTest {
    File goodFile = new File("");
    XMLBeansParser xmlBeanParser = null;

    @Before
    public void CreateXMLBeanParseTest() throws Exception {
        xmlBeanParser = new XMLBeansParser(goodFile);
        assertNotNull(xmlBeanParser.getFile());
    }

    @Test
    public void parseBeans() throws Exception {
        // TODO: 11.04.2017
    }

    @Test
    public void getMapWithBeans() throws Exception {
       assertTrue(xmlBeanParser.getBeans() instanceof Map);
    }
}
