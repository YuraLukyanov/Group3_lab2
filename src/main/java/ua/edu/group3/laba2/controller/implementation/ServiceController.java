package ua.edu.group3.laba2.controller.implementation;

import java.util.Collection;
import java.util.List;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class ServiceController {

    Collection collection;

    public Collection getCollection() {
        return collection;
    }

    public ServiceController(String httpRequestServiceName,
                             List<String> httpRequestServiceParam) {
        
        createResponseCollection(httpRequestServiceName, httpRequestServiceParam);

    }

    private void createResponseCollection(String httpRequestServiceName,
                                          List<String> httpRequestServiceParam) {
        // TODO: 11.04.2017
    }
}
