package controller;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpRequestTest {
    //http://localhost:port/search?name=&color=green&volume=200&weight=
    String goodRequest = "GET /search?name=&color=green&volume=200&weight= HTTP/1.1\n" +
            "Host: localhost:9998\n"+
            "Connection: keep-alive\n" +
            "Upgrade-Insecure-Requests: 1\n" +
            "User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n" +
            "Accept-Encoding: gzip, deflate, sdch, br\n" +
            "Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4,uk;q=0.2";

    String goodRequestNoParams =  "GET /basket? HTTP/1.1\n" +
            "Host: localhost:9998\n"+
            "Connection: keep-alive\n" +
            "Upgrade-Insecure-Requests: 1\n" +
            "User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n" +
            "Accept-Encoding: gzip, deflate, sdch, br\n" +
            "Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4,uk;q=0.2";

    String goodRequestOneParam =  "GET /basket?some=test& HTTP/1.1\n" +
            "Host: localhost:9998\n"+
            "Connection: keep-alive\n" +
            "Upgrade-Insecure-Requests: 1\n" +
            "User-Agent: Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36\n" +
            "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8\n" +
            "Accept-Encoding: gzip, deflate, sdch, br\n" +
            "Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4,uk;q=0.2";

    @Test
    public void parseRequestServiceNameTest() throws Exception {
        HttpRequest request = new HttpRequest(goodRequest);
        assertEquals("Incorrect parse service name","search", request.getServiceName());

    }

    @Test
    public void parseRequestServiceParamTest() throws Exception {
        HttpRequest request = new HttpRequest(goodRequest);
        List<String> serviceParam = new ArrayList<>();
        serviceParam.add(null);
        serviceParam.add("green");
        serviceParam.add("200");
        serviceParam.add(null);
        for (int i = 0; i <serviceParam.size() ; i++) {
            assertEquals("Element " + i+ " is not equal",
                    serviceParam.get(i),request.getServiceParam().get(i));
        }
    }

    @Test
    public void parseRequestServiceNameWithNoParams() throws Exception {
        HttpRequest request = new HttpRequest(goodRequestNoParams);
        assertEquals("Incorrect parse service name","basket", request.getServiceName());
    }

    @Test
    public void parseRequestServiceNameWithOneParam() throws Exception {
        HttpRequest request = new HttpRequest(goodRequestOneParam);
        List<String> serviceParam = new ArrayList<>();
        serviceParam.add("test");
        assertEquals("Incorrect parse service name","basket", request.getServiceName());
        for (int i = 0; i <request.getServiceParam().size(); i++) {
            assertEquals("Element " + i+ " is not equal",
                    serviceParam.get(i),request.getServiceParam().get(i));
        }

    }
}
