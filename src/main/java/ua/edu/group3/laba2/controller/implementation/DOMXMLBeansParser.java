package ua.edu.group3.laba2.controller.implementation;

import com.google.common.collect.Maps;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import ua.edu.group3.laba2.controller.interfaces.XMLBeansParser;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;


/**
 * Created by Nikolion on 11.04.2017.
 */
public class DOMXMLBeansParser implements XMLBeansParser {
    protected Map<String, Object> beans = Maps.newHashMap();
    protected File file;


    public DOMXMLBeansParser(File file) {
        this.file = file;
    }

    public DOMXMLBeansParser() {
    }

    @Override
    public void setFile(File file) {
        this.file = file;
    }

    @Override
    public Map<String, Object> getBeans() {
        return beans;
    }

    @Override
    public boolean parseBeans() {
        if (file == null) {
            return false;
        }
        ArrayList<Node> beansWithRef = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(file);
            NodeList beansNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beansNodeList.getLength(); i++) {
                Node nodeBean = beansNodeList.item(i);
                if (nodeBean.getNodeType() == Node.ELEMENT_NODE) {

                    Element elementBean = (Element) nodeBean;
                    NodeList propertyNodeListOfBean = elementBean.getElementsByTagName("property");
                    if (propertyNodeListOfBean.getLength() == 0) {
                        String name = ((Element) nodeBean).getAttribute("id");
                        getBeans().put(name, Class.forName(((Element) nodeBean).getAttribute("class")).newInstance());
                    } else {
                        if (isBeanContainsRef(propertyNodeListOfBean)) {
                            beansWithRef.add(nodeBean);
                        } else {
                            parseBeanWithValues(nodeBean);
                        }
                    }
                }
            }
            parseBeansWithRefs(beansWithRef);

        } catch (ParserConfigurationException | SAXException |
                InvocationTargetException | InstantiationException | IOException |
                ClassNotFoundException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected void parseBeanWithValues(Node nodeBean) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        Element elementBean = (Element) nodeBean;
        String name = ((Element) elementBean).getAttribute("id");
        Class beanClass = Class.forName(((Element) elementBean).getAttribute("class"));

        Object obj = Class.forName(((Element) elementBean).getAttribute("class")).newInstance();

        NodeList propertyNodeListOfBean = elementBean.getElementsByTagName("property");
        for (int j = 0; j < propertyNodeListOfBean.getLength(); j++) {
            Element property = (Element) propertyNodeListOfBean.item(j);
            String methodName = "set" + property.getAttribute("name");
            String value = "";
            value = property.getAttribute("value");
            Object val = null;
            if (!value.isEmpty()) {
                val = interpretation(value);
            }

            Method[] methods = beanClass.getMethods();

            for (Method m : methods) {
                if (m.getName().equalsIgnoreCase(methodName)) {
                    Object[] args = new Object[]{val};
                    m.invoke(obj, args);
                }
            }
        }
        getBeans().put(name, obj);
    }

    protected void parseBeansWithRefs(ArrayList<Node> beansWithRef) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException {
        for (int i = 0; i < beansWithRef.size(); i++) {
            Element elementBean = (Element) beansWithRef.get(i);
            String name = ((Element) elementBean).getAttribute("id");
            Class beanClass = Class.forName(((Element) elementBean).getAttribute("class"));

            Object obj = Class.forName(((Element) elementBean).getAttribute("class")).newInstance();


            NodeList propertyNodeListOfBean = elementBean.getElementsByTagName("property");
            for (int j = 0; j < propertyNodeListOfBean.getLength(); j++) {
                Element property = (Element) propertyNodeListOfBean.item(j);
                String methodName = "set" + property.getAttribute("name");
                String value = "";
                String ref = "";
                value = property.getAttribute("value");
                ref = property.getAttribute("ref");
                Object val = null;
                if (!value.isEmpty()) {
                    val = interpretation(value);
                } else if (!ref.isEmpty()) {
                    val = beans.get(ref);
                }

                Method[] methods = beanClass.getMethods();

                for (Method m : methods) {
                    if (m.getName().equalsIgnoreCase(methodName)) {
                        Object[] args = new Object[]{val};
                        m.invoke(obj, args);
                    }
                }
            }
            getBeans().put(name, obj);

        }
    }

    protected boolean isBeanContainsRef(NodeList propertyNodeListOfBean) {
        for (int j = 0; j < propertyNodeListOfBean.getLength(); j++) {
            Element propertyAttribute = (Element) propertyNodeListOfBean.item(j);
            String ref;
            ref = propertyAttribute.getAttribute("ref");
            if (!ref.isEmpty()) {
                return true;
            }
        }
        return false;
    }


    protected Object interpretation(String s) {
        Scanner sc = new Scanner(s);
        return
                sc.hasNextInt() ? sc.nextInt() :
                        sc.hasNextLong() ? sc.nextLong() :
                                sc.hasNextDouble() ? sc.nextDouble() :
                                        sc.hasNext() ? sc.next() :
                                                s;
    }
}
