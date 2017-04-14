package ua.edu.group3.laba2.controller.implementation;

import com.google.common.collect.Maps;

import java.io.File;
import java.util.Map;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class XMLBeansParser {
    Map<String, Object> beans = Maps.newHashMap();
    private final File file;

    public XMLBeansParser(File file) {
        this.file = file;
    }

    public Map<String,Object> getBeans() {
        return beans;
    }
    public boolean parseBeans(){
        return false;
    }

    public File getFile() {
        return file;
    }

}
