package view;

import controller.ServiceController;
import javax.management.ServiceNotFoundException;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

public class HttpServerResponse {
    private String response = "";

    public String getResponse() {
        return response;
    }

    public HttpServerResponse(String httpRequestServiceName,
                              Map<String, String> httpRequestServiceParam) {
        ServiceController serviceController;
        try {
            serviceController = new ServiceController(
                    httpRequestServiceName, httpRequestServiceParam);
            String content = convertObjectToHtml(serviceController.getCollection());
            createResponse(content, 200);
        } catch (ServiceNotFoundException e) {
            String serviceNotFound = "<html><body><h1>404 Not " +
                    "Found</h1></html></body>";
            createResponse(serviceNotFound, 404);
        } catch (Exception e){
            String serverError = "<html><body><h1>501 Server " +
                    "Error</h1></html></body>";
            createResponse(serverError, 501);
        }

    }

    private void createResponse(String content, int code) {
        response = createHeadOfResponse(content.length(), code) + content;

    }

    private static String createHeadOfResponse(int contentLength, int code) {
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
            default: /*NOP*/
        }
        return "HTTP/1.1 " + codeString + "\r\n" +
                "Date: " + new Date().toLocaleString() + "\r\n" +
                "Server: MY SERVER\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + contentLength + "\r\n" +
                "Connection: Closed\r\n\r\n";
    }

    @SuppressWarnings("WeakerAccess")
    protected static String convertObjectToHtml(Object responseObj) {
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
