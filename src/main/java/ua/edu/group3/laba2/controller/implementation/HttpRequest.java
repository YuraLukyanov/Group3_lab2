package ua.edu.group3.laba2.controller.implementation;

import java.util.List;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpRequest {
    public String getServiceName() {
        return serviceName;
    }

    public List<String> getServiceParam() {
        return serviceParam;
    }

    HttpRequest(String request) {
        parseStringRequestToValues(request);
    }

    private String serviceName;
    private List<String> serviceParam;

    public void parseStringRequestToValues(String request){
        // TODO: 11.04.2017
    }
}
