package ua.edu.group3.laba2.controller.interfaces;

import java.io.File;
import java.util.Map;

/**
 * Created by Nikolion on 29.04.2017.
 */
public interface XMLBeansParser {
    void setFile(File file);

    Map<String, Object> getBeans();

    boolean parseBeans();
}
