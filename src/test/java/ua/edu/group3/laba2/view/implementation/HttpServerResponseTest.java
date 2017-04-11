package ua.edu.group3.laba2.view.implementation;


import org.junit.Test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpServerResponseTest {

    @Test
    public void convertCollectionToHtml() throws Exception {
        String result = "<ol>" +
                "<li>null</li>" +
                "<li>green</li>" +
                "<li>200</li>" +
                "<li>null</li>" +
                "</ol>";
        String serviceName = "search";
        List<String> serviceParam = new ArrayList<>();
        serviceParam.add(null);
        serviceParam.add("green");
        serviceParam.add("200");
        serviceParam.add(null);
        HttpServerResponse httpServerResponse = new HttpServerResponse(serviceName, serviceParam);
        String serverResponse = httpServerResponse.convertCollectionToHtml(serviceParam);

        assertEquals("Response is not right", result, serverResponse);

    }
}
