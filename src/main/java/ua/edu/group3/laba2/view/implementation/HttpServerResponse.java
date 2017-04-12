package ua.edu.group3.laba2.view.implementation;

import ua.edu.group3.laba2.controller.implementation.HttpRequest;
import ua.edu.group3.laba2.controller.implementation.ServiceController;

import java.util.Collection;
import java.util.List;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpServerResponse {
    private String response;
    private String content;

    public String getResponse() {
        return response;
    }

    public HttpServerResponse(String httpRequestServiceName,
                              List<String> httpRequestServiceParam) {

        ServiceController serviceController = new ServiceController(
                httpRequestServiceName,httpRequestServiceParam);

        content = convertCollectionToHtml(serviceController.getCollection());
        createResponse(content);
    }

    private void createResponse(String content) {
    }

    public static String convertCollectionToHtml(Collection collection){
        return null;
    }

}
