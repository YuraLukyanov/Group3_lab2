package view;


import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
        List<String> serviceParam = new ArrayList<>();
        serviceParam.add(null);
        serviceParam.add("green");
        serviceParam.add("200");
        serviceParam.add(null);

        String serverResponse = HttpServerResponse.convertCollectionToHtml(serviceParam);

        assertEquals("Response is not right", result, serverResponse);

    }
}
