package view;


import controller.ServiceController;
import javax.management.ServiceNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpServerResponse {
    private String response = "";
    private String content = "";
    final String serviceNotFound = "<html><body><h1>404 Not " +
            "Found</h1></html></body>";
    final String serverError = "<html><body><h1>501 Server " +
            "Error</h1></html></body>";

    public String getResponse() {
        return response;
    }

    public HttpServerResponse(String httpRequestServiceName,
                              Map<String, String> httpRequestServiceParam) {
        ServiceController serviceController = null;
        try {
            serviceController = new ServiceController(
                    httpRequestServiceName, httpRequestServiceParam);
            content = convertCollectionToHtml(serviceController.getCollection());
            createResponse(content, 200);
        } catch (ServiceNotFoundException e) {
            createResponse(serviceNotFound, 404);
        } catch (Exception e){
            createResponse(serverError, 501);
        }

    }

    protected void createResponse(String content, int code) {
        response = createHeadOfResponse(content.length(), code) + content;

    }

    protected String createHeadOfResponse(int contentLength, int code) {
        String codeString = "";
        switch (code) {
            case 200:
                codeString = "200 OK";
                break;
            case 404:
                codeString = "404 Not Found";
                break;
            case 501:
                codeString = "501 Not Implemented";
                break;
        }
        String responseHead = "HTTP/1.1 " + codeString + "\r\n" +
                "Date: " + new Date().toLocaleString() + "\r\n" +
                "Server: MY SERVER\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + contentLength + "\r\n" +
                "Connection: Closed\r\n\r\n";
        return responseHead;
    }

    public static String convertCollectionToHtml(Object responseObj) {
        if (responseObj instanceof Collection) {
            Collection collection = (Collection) responseObj;
            StringBuilder builder = new StringBuilder();
            builder.append("<html><body><ol>");
            for (Object element : collection) {
                if (element == null) {
                    builder.append("<li>null</li>");
                } else
                    builder.append("<li>").append(element.toString()).append("</li>");
            }
            builder.append("</ol></body></html>");
            return builder.toString();
        } else
            return "<html><body>" + responseObj.toString() + "</body></html>";

    }

}
