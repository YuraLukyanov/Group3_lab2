package controller;

import com.google.common.collect.Maps;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
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
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(file);
            Bean bean = null;
            Map<String, Bean> mapWithTempBeans = new HashMap<>();
            NodeList beansNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beansNodeList.getLength(); i++) {
                Node nodeBean = beansNodeList.item(i);
                if (nodeBean.getNodeType() == Node.ELEMENT_NODE) {
                    Element elementBean = (Element) nodeBean;
                    String name = elementBean.getAttribute("id");
                    String className = elementBean.getAttribute("class");

                    bean = new Bean(name, className);

                    NodeList propertyNodeListOfBean = elementBean.getElementsByTagName("property");
                    bean = parseProperty(bean, propertyNodeListOfBean);
                    mapWithTempBeans.put(bean.id, bean);
                }
            }
            createBeans(mapWithTempBeans);

        } catch (ParserConfigurationException | SAXException |
                IllegalArgumentException | InstantiationException |
                IOException | ClassNotFoundException |
                InvocationTargetException |
                IllegalAccessException e) {
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

    protected Bean parseProperty(Bean bean, NodeList propertyNodeListOfBean) {
        for (int i = 0; i < propertyNodeListOfBean.getLength(); i++) {
            Element property = (Element) propertyNodeListOfBean.item(i);
            String name = property.getAttribute("name");
            if (!property.getAttribute("value").isEmpty()) {
                bean.propertyVal.put(name, property.getAttribute("value"));
                continue;
            } else if (!property.getAttribute("ref").isEmpty()) {
                bean.propertyRef.put(name, property.getAttribute("ref"));
            }
        }
        return bean;
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

        Bean(String id, String className) throws NullPointerException {
            if (id.isEmpty() || className.isEmpty()) {
                throw new NullPointerException();
            }
            this.id = id;
            this.className = className;
        }

        String id;
        String className;
        Map<String, String> propertyVal = new HashMap<>();
        Map<String, String> propertyRef = new HashMap<>();
    }
}
