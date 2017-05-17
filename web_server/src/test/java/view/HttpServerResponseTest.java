package view;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpServerResponseTest {

    @Test
    public void convertCollectionToHtmlTest() throws Exception {
        String result = "<html><body><ol>" +
                "<li>null</li>" +
                "<li>green</li>" +
                "<li>200</li>" +
                "<li>null</li>" +
                "</ol></body></html>";
        List<String> serviceParam = new ArrayList<>();
        serviceParam.add(null);
        serviceParam.add("green");
        serviceParam.add("200");
        serviceParam.add(null);

        String serverResponse = HttpServerResponse.convertObjectToHtml(serviceParam);

        assertEquals("Response is not right", result, serverResponse);
    }

    @Test
    public void convertObjectToHtmlTest() throws Exception {
        String resultBolean = "<html><body>true</body></html>";
        String resultInt = "<html><body>100</body></html>";

        assertEquals("Response is not right", resultBolean,
                HttpServerResponse.convertObjectToHtml(true));
        assertEquals("Response is not right", resultInt,
                HttpServerResponse.convertObjectToHtml(100));
    }

    @Test
    public void fullRequestServiceNotFoundTest() throws Exception {
        String httpServiceName = "testService";
        Map<String, String> httpServiceParam = new HashMap<>();
        httpServiceParam.put("method","someMethod");

        HttpServerResponse httpServerResponse = new HttpServerResponse
                (httpServiceName,httpServiceParam);

        String content = "<html><body><h1>404 Not Found</h1></html></body>";
        String expectedResponse = "HTTP/1.1 404 Not Found\r\n" +
                "Date: " + new Date().toLocaleString() + "\r\n" +
                "Server: MY SERVER\r\n" +
                "Content-Type: text/html\r\n" +
                "Content-Length: " + content.length() + "\r\n" +
                "Connection: Closed\r\n\r\n" + content;

        assertEquals(expectedResponse,httpServerResponse.getResponse());
    }
}
