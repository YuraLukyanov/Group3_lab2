package ua.edu.group3.laba2.controller.implementation;

import java.io.File;
import java.util.Collection;

/**
 * Created by Nikolion on 10.04.2017.
 */
public class Container {
    private static final Container container = new Container();
    private File file;

    //синхронизация?
    public static Container getInstance() {
        return container;
    }

    public boolean init(File file) {
        return false;
    }

    public Object getBean(String name) {
        return null;
    }

    public <T> Object getBean(String name, Class<T> type) {
        return null;
    }

    public <T> Collection getBean(Class<T> type) {
        return null;
    }

    public File getFile() {
        return file;
    }

    public boolean refresh() {

        return false;
    }

}
