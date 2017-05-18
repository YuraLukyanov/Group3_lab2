package controller;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpRequestTest {

    String goodRequest = "GET /search?method=add&name=&color=green&volume=200&weight= HTTP/1.1\n" +
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

    String goodRequestOneParam =  "GET /basket?method=getAll& HTTP/1.1\n" +
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

        assertEquals("add",request.getServiceParam().get("method"));
        assertEquals(null,request.getServiceParam().get("name"));
        assertEquals("green",request.getServiceParam().get("color"));
        assertEquals("200",request.getServiceParam().get("volume"));
        assertEquals(null,request.getServiceParam().get("weight"));
    }

    @Test
    public void parseRequestServiceNameWithNoParams() throws Exception {
        HttpRequest request = new HttpRequest(goodRequestNoParams);
        assertEquals("Incorrect parse service name","basket", request.getServiceName());
    }

    @Test
    public void parseRequestServiceNameWithOneParam() throws Exception {
        HttpRequest request = new HttpRequest(goodRequestOneParam);
        assertEquals("getAll",request.getServiceParam().get("method"));

    }
}
