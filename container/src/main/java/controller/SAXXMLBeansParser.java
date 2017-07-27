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
import java.util.HashMap;
import java.util.Map;

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
            final Map<String, IBean> mapWithTempBeans = new HashMap<>();
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
            beans = new BeanCreator(mapWithTempBeans).getResultBeans();

        } catch (ParserConfigurationException | SAXException | IOException |
                IllegalAccessException |  InstantiationException |
                ClassNotFoundException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
        return true;
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
