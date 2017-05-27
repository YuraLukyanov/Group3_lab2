package controller;

import java.util.Map;

/**
 * Interface to get information about bean
 */
public interface IBean {
    /**
     * Method return map with values property.
     * Where key is name of value and  value is value of property
     */
    Map<String, String> getPropertyVal();

    /**
     * Method return map with reference property.
     * Where key is name of reference and  value is id of the some bean
     */
    Map<String, String> getPropertyRef();

    /**
     * Method return id of the bean.
     */
    String getID();

    /**
     * Method return class name of the bean.
     */
    String getClassName();

}