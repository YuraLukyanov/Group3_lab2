package controller;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.HashMap;
import java.util.Map;


public class DOMXMLBeansParser implements XMLBeansParser {
    private static final Logger LOGGER = LoggerFactory.getLogger(DOMXMLBeansParser.class);
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
            Map<String, IBean> mapWithTempBeans = new HashMap<>();
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
            beans = new BeanCreator(mapWithTempBeans).getResultBeans();

        } catch (ParserConfigurationException | SAXException |
                IllegalArgumentException | InstantiationException |
                IOException | ClassNotFoundException |
                InvocationTargetException |
                IllegalAccessException e) {
            LOGGER.error("Parse exception",e);
            e.printStackTrace();
            return false;
        }
        return true;
    }
    /**
     * Method of parsing the parameters of the bean and adding them to the current bean
     *
     * @param bean              bean without properties
     * @param propertyNodeListOfBean NodeList with all properties of bean
     *                               @return bean with properties
     */
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

    /**
     * Class for storing data about bean
     */
    private class Bean implements IBean{
        protected String id;
        @SuppressWarnings("WeakerAccess")
        protected String className;
        @SuppressWarnings("WeakerAccess")
        protected Map<String, String> propertyVal = new HashMap<>();
        @SuppressWarnings("WeakerAccess")
        protected Map<String, String> propertyRef = new HashMap<>();

        Bean(String id, String className) throws NullPointerException {
            if (id.isEmpty() || className.isEmpty()) {
                throw new NullPointerException();
            }
            this.id = id;
            this.className = className;
        }

        @Override
        public Map<String, String> getPropertyVal() {
            return propertyVal;
        }

        @Override
        public Map<String, String> getPropertyRef() {
            return propertyRef;
        }

        @Override
        public String getID() {
            return id;
        }

        @Override
        public String getClassName() {
            return className;
        }
    }
}
