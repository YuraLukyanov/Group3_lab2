package controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class HttpRequest {
    public String getServiceName() {
        return serviceName;
    }

    public List<String> getServiceParam() {
        return serviceParam;
    }

    HttpRequest(String request) {
        parseStringRequestToValues(request);
    }

    private String serviceName;
    private List<String> serviceParam = new ArrayList<>();

    protected void parseStringRequestToValues(String request) {
        try {
            String[] requestRows = request.split(System.getProperty("line.separator"));
            String requestType = requestRows[0].split(" ")[0];
            if (requestType.equals("GET")) {
                //System.out.println(requestRows[0]);
                parseGet(requestRows[0]);
            } else {
                throw new UnsupportedOperationException("No available this request");
            }
        } catch (Throwable e) {

        }
    }

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
                        try {
                            paramValue = param.split("\\=")[1];
                            serviceParam.add(paramValue);
                        } catch (IndexOutOfBoundsException e) {
                            serviceParam.add(null);
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
