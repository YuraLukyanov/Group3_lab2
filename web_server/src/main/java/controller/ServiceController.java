package controller;

import com.thoughtworks.paranamer.AnnotationParanamer;
import com.thoughtworks.paranamer.BytecodeReadingParanamer;
import com.thoughtworks.paranamer.CachingParanamer;
import com.thoughtworks.paranamer.Paranamer;
import model.implementetion.services.Busket;
import model.implementetion.services.ProductManager;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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
                             Map<String, String> httpRequestServiceParam) {

        createResponseCollection(httpRequestServiceName, httpRequestServiceParam);

    }

    private void createResponseCollection(String httpRequestServiceName,
                                          Map<String, String> httpRequestServiceParam) {

        Object result = null;
        try {
            Object service = Container.getInstance().getBean(httpRequestServiceName);

            //test configuration
            /*Busket busket = new Busket();
            ProductManager productManager = new ProductManager();
            busket.setProductManager(productManager);
            Object service = busket;*/

            Method[] methods = service.getClass().getMethods();
            String methodName = httpRequestServiceParam.get("method");
            for (Method m : methods) {
                Object[] args;
                if (m.getName().equalsIgnoreCase(methodName)) {
                    Parameter[] p = m.getParameters();
                    args = new Object[p.length];
                    String name = "";

                    Paranamer info = new CachingParanamer(new AnnotationParanamer(new BytecodeReadingParanamer()));
                    String[] parameterNames = info.lookupParameterNames(m);
                    for (int i = 0; i < parameterNames.length; i++) {
                        name = parameterNames[i];
                        Object o = httpRequestServiceParam.get(name);
                        args[i] = castToType(o.toString());
                    }
                    //get argument name with compilation argument: -parameters
                   /* for (int i = 0; i < p.length; i++) {
                        name = p[i].getName();
                        Object o = httpRequestServiceParam.get(name);
                        args[i] = castToType(o.toString());
                    }*/

                    result = m.invoke(service, args);
                    break;
                }
            }

            serviceResponse = result;
        } catch (Exception e) {
            e.printStackTrace();
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
        collection = tempResponseCollection;*/
    }

    protected Object castToType(String s) {
        Scanner sc = new Scanner(s);
        return
                sc.hasNextInt() ? sc.nextInt() :
                        sc.hasNextDouble() ? sc.nextDouble() :
                                sc.hasNext() ? sc.next() :
                                        s;
    }

}
