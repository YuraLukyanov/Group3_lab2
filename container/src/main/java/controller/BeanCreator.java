package controller;

import com.google.common.collect.Maps;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Nikolion on 27.05.2017.
 */
public class BeanCreator {


    protected Map<String, Object> beans = Maps.newHashMap();
    /**
     * Constructor
     * @param mapWithTempBeans map with IBean, where key is id of bean
     */
    BeanCreator(Map<String, IBean> mapWithTempBeans) throws
            ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        createBeans(mapWithTempBeans);
    }

    public Map<String, Object> getResultBeans() {
        return beans;
    }

    protected void createBeans(Map<String, IBean> mapWithTempBeans) throws
            ClassNotFoundException, InvocationTargetException,
            InstantiationException, IllegalAccessException {

        for (Map.Entry<String, IBean> entry : mapWithTempBeans.entrySet()) {
            if (beans.get(entry.getKey()) == null) {
                tryCreateBean(entry.getKey(), mapWithTempBeans);
            } else continue;
        }
    }

    /**
     * Method recursively creates beans and adds them to the output map
     * for the container
     *
     * @param key              id of bean
     * @param mapWithTempBeans map with objects of class Bean
     */
    protected void tryCreateBean(String key, Map<String, IBean>
            mapWithTempBeans) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException,
            InvocationTargetException, IllegalArgumentException {
        IBean bean = mapWithTempBeans.get(key);
        Class beanClass = Class.forName(mapWithTempBeans.get(key).getClassName());
        Object beanObj = beanClass.newInstance();
        Method[] methods = beanClass.getMethods();

        for (Map.Entry<String, String> entryValueProperty : bean.getPropertyVal().entrySet()) {
            Object val = interpretation(entryValueProperty.getValue());
            for (Method m : methods) {
                if (m.getName().equalsIgnoreCase("set" + entryValueProperty.getKey())) {
                    Object[] args = new Object[]{val};
                    m.invoke(beanObj, args);
                    break;
                }
            }
        }

        for (Map.Entry<String, String> entryRefProperty : bean.getPropertyRef().entrySet()) {
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

}
