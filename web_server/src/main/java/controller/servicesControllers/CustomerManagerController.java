package controller.servicesControllers;

import model.interfaces.services.ICustomerManager;
import model.pojo.Customer;
import util.UtilClass;

import java.util.Map;

/**
 * Created by Nikolion on 17.05.2017.
 */
public class CustomerManagerController implements StartService {
    @Override
    public Object runService(Object service, String method, Map<String, String> httpRequestServiceParam) throws Exception {
        ICustomerManager customerManager = (ICustomerManager) service;
        Object result = null;
        String name;
        String login;
        String password;

        switch (method) {
            case ("get"):
                result = customerManager.get((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")));
                break;
            case ("getByName"):
                result = customerManager.getByName(httpRequestServiceParam
                        .get("name"));
                break;
            case ("delete"):
                result = customerManager.delete((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")));
                break;
            case ("getAll"):
                result = customerManager.getAll();
                break;
            case ("update"):
                 name = httpRequestServiceParam.get("name");
                 login = httpRequestServiceParam.get("login");
                 password =httpRequestServiceParam.get("password");

                result = customerManager.update((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")),name,login,password);
                break;
            case ("add"):
                 name = httpRequestServiceParam.get("name");
                 login = httpRequestServiceParam.get("login");
                 password =httpRequestServiceParam.get("password");


                result = customerManager.add(name,login,password);
                break;
        }
        return result;
    }
}
