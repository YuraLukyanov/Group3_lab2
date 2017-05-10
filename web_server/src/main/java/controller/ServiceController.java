package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class ServiceController {

    Collection collection;

    public Collection getCollection() {
        return collection;
    }

    public ServiceController(String httpRequestServiceName,
                             Map<String,String> httpRequestServiceParam) {

        createResponseCollection(httpRequestServiceName, httpRequestServiceParam);

    }

    private void createResponseCollection(String httpRequestServiceName,
                                          Map<String,String> httpRequestServiceParam) {

        /*switch (httpRequestServiceName) {
            case ("search"): //do something...
            case ("product"): //do something...
            case ("addProduct"): //do something...
            case ("recommendations"): //do something...
            default://do something...
        }*/

        // TODO: 11.04.2017
        //TEMP realization
        //Example: http://localhost:9999/search?product=yes&price=100500&
        ArrayList<String> tempResponseCollection = new ArrayList<>();
        tempResponseCollection.add("service=" + httpRequestServiceName);
        if (!httpRequestServiceParam.isEmpty()) {
            for (Map.Entry<String,String> element : httpRequestServiceParam.entrySet()) {
                tempResponseCollection.add(element.getKey() + "=" + element.getValue());
            }
        }
        collection = tempResponseCollection;
    }
}
