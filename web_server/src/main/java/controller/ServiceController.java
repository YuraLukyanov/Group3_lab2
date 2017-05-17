package controller;


import controller.exceptions.WebServerException;
import controller.servicesControllers.BusketController;
import model.implementetion.services.Authorization;
import model.interfaces.services.IAuthorization;
import model.interfaces.services.IBusket;
import model.interfaces.services.ICustomerManager;

import javax.management.ServiceNotFoundException;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Nikolion on 11.04.2017.
 */
public class ServiceController {

    Object serviceResponse;

    public Object getCollection() {
        return serviceResponse;
    }

    public ServiceController(String httpRequestServiceName,
                             Map<String, String> httpRequestServiceParam)
            throws ServiceNotFoundException, WebServerException {

        createResponseCollection(httpRequestServiceName, httpRequestServiceParam);

    }

    private void createResponseCollection(String httpRequestServiceName,
                                          Map<String, String>
                                                  httpRequestServiceParam) throws ServiceNotFoundException, WebServerException {

        Object result = null;
        try {
            String method = httpRequestServiceParam.get("method");
            Object service = Container.getInstance().getBean(httpRequestServiceName);
            if (service==null || method == null || method.isEmpty()) {
                throw new NullPointerException();
            }
            String serviceClassName = service.getClass().getSimpleName();
            switch (serviceClassName){
                case ("Basket"):
                   result = new BusketController().runService(service,method,httpRequestServiceParam);
                    break;
                case ("Authorization"):
                    IAuthorization authorization = (IAuthorization) service;
                    break;
                case ("CustomManager"):
                    ICustomerManager customerManager = (ICustomerManager) service;
                    break;
                case ("OrderManager"):
                    break;
                case ("ProductManager"):
                    break;
                case ("Recomandations"):
                    break;
            }


            if (result == null) {
                throw new NullPointerException();
            }
            serviceResponse = result;
        } catch (NullPointerException e) {
            e.printStackTrace();
            throw new ServiceNotFoundException();
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebServerException(e);
        }
        /*
        //TEMP realization
        //Example: http://localhost:9999/search?product=yes&price=100500&
        ArrayList<String> tempResponseCollection = new ArrayList<>();
        tempResponseCollection.add("service=" + httpRequestServiceName);
        if (!httpRequestServiceParam.isEmpty()) {
            for (Map.Entry<String,String> element : httpRequestServiceParam.entrySet()) {
                tempResponseCollection.add(element.getKey() + "=" + element.getValue());
            }
        }
        serviceResponse = tempResponseCollection;*/
    }

    static Object castToType(String s) {
        Scanner sc = new Scanner(s);
        return
                sc.hasNextInt() ? sc.nextInt() :
                        sc.hasNextDouble() ? sc.nextDouble() :
                                sc.hasNext() ? sc.next() :
                                        s;
    }

}
