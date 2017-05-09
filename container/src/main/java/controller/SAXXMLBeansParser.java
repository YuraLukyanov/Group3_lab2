package controller;

import com.google.common.collect.Maps;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Nikolion on 30.04.2017.
 */
public class SAXXMLBeansParser implements XMLBeansParser {
    protected Map<String, Object> beans = Maps.newHashMap();
    protected File file;

    public SAXXMLBeansParser(File file) {
        this.file = file;
    }

    public SAXXMLBeansParser() {
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Map<String, Object> getBeans() {
        return beans;
    }

    public boolean parseBeans() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            final Map<String, Bean> mapWithTempBeans = new HashMap<>();
            DefaultHandler handler = new DefaultHandler() {
                Bean bean = new Bean();

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

                    if (qName.equalsIgnoreCase("bean")) {
                        bean.id = attributes.getValue("id");
                        bean.className = attributes.getValue("class");

                    }
                    if (qName.equalsIgnoreCase("property")) {
                        String name = attributes.getValue("name");
                        if (attributes.getValue("value") != null) {
                            bean.propertyVal.put(name, attributes.getValue
                                    ("value"));
                        } else if (attributes.getValue("ref") != null) {
                            bean.propertyRef.put(name, attributes.getValue("ref"));
                        }
                    }

                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                        if (qName.equalsIgnoreCase("bean")) {
                            mapWithTempBeans.put(bean.id,bean);
                            bean = new Bean();
                        }
                }
            };

            parser.parse(file, handler);
            createBeans(mapWithTempBeans);

        } catch (ParserConfigurationException | SAXException | IOException |
                IllegalAccessException |  InstantiationException |
                ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    protected void createBeans(Map<String, Bean> mapWithTempBeans) throws
            ClassNotFoundException, InstantiationException,
            IllegalAccessException, InvocationTargetException, IllegalArgumentException {
        for (Map.Entry<String, Bean> entry : mapWithTempBeans.entrySet()) {
            if (beans.get(entry.getKey()) == null) {
                tryCreateBean(entry.getKey(), mapWithTempBeans);
            } else continue;
        }
    }

    protected void tryCreateBean(String key, Map<String, Bean>
            mapWithTempBeans)  throws ClassNotFoundException,
            IllegalAccessException, InstantiationException,
            InvocationTargetException, IllegalArgumentException {
        Bean bean = mapWithTempBeans.get(key);
        Class beanClass = Class.forName(mapWithTempBeans.get(key).className);
        Object beanObj = beanClass.newInstance();
        Method[] methods = beanClass.getMethods();

        for (Map.Entry<String, String> entryValueProperty : bean.propertyVal.entrySet()) {
            Object val = interpretation(entryValueProperty.getValue());
            for (Method m : methods) {
                if (m.getName().equalsIgnoreCase("set" + entryValueProperty.getKey())) {
                    Object[] args = new Object[]{val};
                    m.invoke(beanObj, args);
                    break;
                }
            }
        }

        for (Map.Entry<String, String> entryRefProperty : bean.propertyRef.entrySet()) {
            Object ref = beans.get(entryRefProperty.getValue());
            if (ref == null) {
                if (mapWithTempBeans.containsKey(entryRefProperty.getValue())) {
                    tryCreateBean(entryRefProperty.getValue(), mapWithTempBeans);
                } else {
                    throw new IllegalArgumentException();
                }
                ref = beans.get(entryRefProperty.getValue());
            }
            for (Method m : methods) {
                if (m.getName().equalsIgnoreCase("set" + entryRefProperty.getKey())) {
                    Object[] args = new Object[]{ref};
                    m.invoke(beanObj, args);
                    break;
                }
            }
        }
        beans.put(key, beanObj);
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

    private class Bean {
        String id;
        String className;
        Map<String, String> propertyVal = new HashMap<>();
        Map<String, String> propertyRef = new HashMap<>();
    }
}
