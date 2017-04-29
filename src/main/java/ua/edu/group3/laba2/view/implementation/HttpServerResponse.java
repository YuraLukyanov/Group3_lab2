package ua.edu.group3.laba2.view.implementation;

import ua.edu.group3.laba2.controller.implementation.ServiceController;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpServerResponse {
    private String response="";
    private String content="";

    public String getResponse() {
        return response;
    }

    public HttpServerResponse(String httpRequestServiceName,
                              List<String> httpRequestServiceParam) {

        ServiceController serviceController = new ServiceController(
                httpRequestServiceName, httpRequestServiceParam);
        content = convertCollectionToHtml(serviceController.getCollection());
        createResponse(content);
    }

    protected void createResponse(String content) {
        StringBuilder htmlPage = new StringBuilder();
        htmlPage.append("<html><body>");
        htmlPage.append(content);
        htmlPage.append("</body></html>");
        String response1 = "HTTP/1.1 200 OK\r\n" +
                "Date: " + new Date().toLocaleString() +"\r\n" +
                "Server: MY SERVER\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + htmlPage.length() + "\r\n" +
                "Connection: Closed\r\n\r\n";
        response = response1 + htmlPage;

    }

    public static String convertCollectionToHtml(Collection collection) {
        StringBuilder builder = new StringBuilder();
        builder.append("<ol>");
        for (Object element : collection) {
            if (element == null) {
                builder.append("<li>null</li>");
            } else
                builder.append("<li>").append(element.toString()).append("</li>");
        }
        builder.append("</ol>");
        return builder.toString();
    }

}
