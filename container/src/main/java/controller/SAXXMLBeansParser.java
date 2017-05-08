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
    private ArrayList<Bean> beansWithRef = new ArrayList<>();
    File file;

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
                            bean.property.put(name, attributes.getValue("value"));
                        } else if (attributes.getValue("ref") != null) {
                            bean.propertyRef.put(name, attributes.getValue("ref"));
                        }
                    }

                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    try {
                        if (qName.equalsIgnoreCase("bean")) {
                            if (bean.propertyRef.size() > 0) {

                                beansWithRef.add(bean);

                            } else if (bean.property.size() > 0) {

                                String name = bean.id;
                                Object obj = Class.forName(bean.className).newInstance();
                                getBeans().put(name,setBeanValuesProperty(obj,bean.property));

                            } else {
                                getBeans().put(bean.id, Class.forName(bean.className).newInstance());
                            }
                            bean = new Bean();
                        }
                    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                        throw new SAXException(e);
                    }

                }
            };

            parser.parse(file, handler);

            for (Bean bean : beansWithRef) {
                String name = bean.id;
                Object obj = Class.forName(bean.className).newInstance();
                obj = setBeanValuesProperty(obj,bean.property);
                obj = setBeanRefProperty(obj,bean.propertyRef);
                getBeans().put(name,obj);
            }

        } catch (ParserConfigurationException | SAXException | IOException |
                IllegalAccessException | InstantiationException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return true;
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

    protected Object setBeanValuesProperty(Object obj, Map<String, String> property) {
        try {
            for (Map.Entry<String, String> entry : property.entrySet()) {
                String methodName = "set" + entry.getKey();
                String value = entry.getValue();
                Object val = null;
                if (!value.isEmpty()) {
                    val = interpretation(value);
                }
                Class beanClass = obj.getClass();
                Method[] methods = beanClass.getMethods();

                for (Method m : methods) {
                    if (m.getName().equalsIgnoreCase(methodName)) {
                        Object[] args = new Object[]{val};
                        m.invoke(obj, args);
                    }
                }

            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }
    protected Object setBeanRefProperty(Object obj, Map<String, String> propertyRef) {
        try {
            for (Map.Entry<String, String> entry : propertyRef.entrySet()) {
                String methodName = "set" + entry.getKey();
                String ref = entry.getValue();
                Object refObject = beans.get(ref);
                if (refObject!=null) {
                    Class beanClass = obj.getClass();
                    Method[] methods = beanClass.getMethods();

                    for (Method m : methods) {
                        if (m.getName().equalsIgnoreCase(methodName)) {
                            Object[] args = new Object[]{refObject};
                            m.invoke(obj, args);
                        }
                    }
                }


            }
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return obj;
    }

    private class Bean {
        String id;
        String className;
        Map<String, String> property = new HashMap<>();
        Map<String, String> propertyRef = new HashMap<>();
    }
}
