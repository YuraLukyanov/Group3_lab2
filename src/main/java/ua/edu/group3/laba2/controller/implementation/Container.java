package ua.edu.group3.laba2.controller.implementation;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import ua.edu.group3.laba2.controller.interfaces.XMLBeansParser;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by Nikolion on 10.04.2017.
 */
public class Container {
    private static final Container container = new Container();
    private String pathToFile;
    protected Map<String, Object> beans = Maps.newHashMap();

    //синхронизация?
    public static Container getInstance() {
        return container;
    }

    public boolean init(String pathToFile) {
        File fileWithXML = new File(pathToFile);
        if (fileWithXML.exists()) {
            this.pathToFile = pathToFile;
            XMLBeansParser xmlBeansParser = new DOMXMLBeansParser();
            //XMLBeansParser xmlBeansParser = new SAXXMLBeansParser();
            xmlBeansParser.setFile(fileWithXML);


            if (xmlBeansParser.parseBeans()) {
                beans = xmlBeansParser.getBeans();
                return true;
            } else {
                return false;
            }
        } else return false;


    }

    public Object getBean(String name) {
        return beans.get(name);
    }

    public <T> T getBean(String name, Class<T> type) {
        return type.cast(beans.get(name));
    }

    public <T> Collection getBean(final Class<T> type) {

        Predicate<Map.Entry<String, Object>> byClass = new Predicate<Map.Entry<String, Object>>() {
            @Override
            public boolean apply(Map.Entry<String, Object> input) {
                return input.getValue().getClass().equals(type);
            }
        };

        Map<String, Object> resultMap = Maps.filterEntries(beans, byClass);
        List<T> resultCollection = (List<T>) mapToList(resultMap);

        return resultCollection;
    }

    protected <T> List mapToList(final Map<String, T> input) {
        return Lists.newArrayList(
                Iterables.transform(
                        input.entrySet(), new Function<Map.Entry<String, T>, T>() {
                            @Override
                            public T apply(final Map.Entry<String, T> input) {
                                return input.getValue();
                            }
                        }));
    }

    public boolean refresh() {
        return !pathToFile.isEmpty() && init(pathToFile);
    }

}
