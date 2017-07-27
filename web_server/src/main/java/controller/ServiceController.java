package controller;


import controller.annotations.ControllerForService;
import controller.annotations.StartServiceMethod;
import controller.exceptions.WebServerException;
import net.sf.corn.cps.CPScanner;
import net.sf.corn.cps.ClassFilter;
import net.sf.corn.cps.PackageNameFilter;

import javax.management.ServiceNotFoundException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class ServiceController {

    private Object serviceResponse;

    public Object getCollection() {
        return serviceResponse;
    }

    public ServiceController(String httpRequestServiceName,
                             Map<String, String> httpRequestServiceParam)
            throws ServiceNotFoundException, WebServerException {

        createResponseCollection(httpRequestServiceName, httpRequestServiceParam);

    }

    /**
     * Method launches the appropriate service by name
     *
     * @param httpRequestServiceName  name of service
     * @param httpRequestServiceParam map with params of service
     */
    private void createResponseCollection(String httpRequestServiceName,
                                          Map<String, String>
                                                  httpRequestServiceParam) throws ServiceNotFoundException, WebServerException {

        Object result = null;
        try {
            String method = httpRequestServiceParam.get("method");
            Object service = Container.getInstance().getBean(httpRequestServiceName);
            if (service == null || method == null || method.isEmpty()) {
                throw new NullPointerException();
            }
            String serviceClassName = service.getClass().getSimpleName();

            //Find classes in package "controller.services"
            // with annotations ControllerForService
            List<Class<?>> classes = CPScanner.scanClasses(new PackageNameFilter
                    ("controller.services"), new ClassFilter().appendAnnotation(ControllerForService.class));
            for (Class<?> clazz : classes) {
                ControllerForService controllerObj = clazz.getDeclaredAnnotation(ControllerForService.class);

                //If name in annotations parameter name equal service name,
                //invoke method with annotations "StartServiceMethod"
                if (controllerObj.name().equalsIgnoreCase(serviceClassName)) {
                    Method[] methods = clazz.getMethods();
                    for (Method md : methods) {
                        if (md.isAnnotationPresent(StartServiceMethod.class)) {
                            result = md.invoke(clazz.newInstance(),service,
                                    method,httpRequestServiceParam);
                            break;
                        }
                    }
                    break;
                }

            }



            /*switch (serviceClassName) {
                case ("Basket"):
                    result = new BusketController().runService(service, method, httpRequestServiceParam);
                    break;
                case ("Authorization"):
                    result = new AuthorizationController().runService
                            (service, method, httpRequestServiceParam);
                    break;
                case ("CustomManager"):
                    result = new CustomerManagerController().runService
                            (service, method, httpRequestServiceParam);
                    break;
                case ("OrderManager"):
                    result = new OrderManagerController().runService(service,
                            method, httpRequestServiceParam);
                    break;
                case ("ProductManager"):
                    result = new ProductManagerController().runService
                            (service, method, httpRequestServiceParam);
                    break;
            }*/


            if (result == null) {
                throw new NullPointerException();
            }
            serviceResponse = result;
        } catch (NullPointerException e) {
            //e.printStackTrace();
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

}
