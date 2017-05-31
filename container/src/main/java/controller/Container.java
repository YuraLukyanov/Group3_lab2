package controller;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Container {
    private static final Container container = new Container();
    private String pathToFile;
    protected Map<String, Object> beans = Maps.newHashMap();

    private Container(){}
    
    public synchronized static Container getInstance() {
        return container;
    }

    /**
     * Method for init container from xml file
     *
     * @param pathToFile xml file with beans
     * @return true if the file is successfully parsed
     */
    public boolean init(String pathToFile) {
        try {
            File fileWithXML = new File(this.getClass().getClassLoader()
                    .getResource(pathToFile).getFile());
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

        } catch (Exception e) {
            return false;
        }


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
