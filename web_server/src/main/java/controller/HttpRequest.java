package controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpRequest {
    public String getServiceName() {
        return serviceName;
    }

    public Map<String,String> getServiceParam() {
        return serviceParam;
    }

    HttpRequest(String request) {
        parseStringRequestToValues(request);
    }

    private String serviceName;
    private Map<String,String> serviceParam = new HashMap<>();
    /**
     * Method parse type of request
     *
     * @param request string with request from user browser
     */
    protected void parseStringRequestToValues(String request) {
        try {
            String[] requestRows = request.split(System.getProperty("line.separator"));
            String requestType = requestRows[0].split(" ")[0];
            if (requestType.equals("GET")) {
                parseGet(requestRows[0]);
            } else {
                throw new UnsupportedOperationException("No available this request");
            }
        } catch (Throwable e) {

        }
    }
    /**
     * Method parse row with GET request
     *
     * @param requestRow first row of GET request
     */
    private void parseGet(String requestRow) {
       try {
            String requestString = requestRow.split("GET /")[1].split(" HTTP/")[0];
            String[] requestServiceNameParamRows = requestString.split("\\?");
            if (requestServiceNameParamRows.length >= 1 &&
                    !(requestServiceNameParamRows[0].isEmpty() ||
                            requestServiceNameParamRows[0].matches("\\s+"))) {
                serviceName = requestServiceNameParamRows[0];
                try {
                    String[] params = requestServiceNameParamRows[1].split("\\&");
                    for (String param : params) {
                        String paramValue = "";
                        String pramName = "";
                        try {
                            pramName = param.split("\\=")[0];
                            paramValue = param.split("\\=")[1];
                            serviceParam.put(pramName,paramValue);
                        } catch (IndexOutOfBoundsException e) {
                            serviceParam.put(pramName,null);
                        }

                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    return;
                }
            }

        } catch (Throwable e) {
            e.printStackTrace();
            return;
        }
    }
}
