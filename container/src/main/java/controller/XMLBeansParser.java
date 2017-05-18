package controller;

import java.io.File;
import java.util.Map;

/**
 * Created by Nikolion on 29.04.2017.
 */
public interface XMLBeansParser {
    /**
     * Method set file with beans
     *
     * @param file xml file with beans
     */
    void setFile(File file);

    /**
     * Method parse file with beans
     *
     * @return true if file with beans successfully parsed
     */
    boolean parseBeans();

    /**
     * Method return map with created objects from xml-file
     *
     * @return map with created objects
     */
    Map<String, Object> getBeans();

}
