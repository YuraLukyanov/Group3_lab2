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
        Customer customer;
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
            case ("getByFilter"):
                customer = new Customer();
                customer.setName(httpRequestServiceParam.get("name"));
                customer.setLogin(httpRequestServiceParam.get("login"));
                customer.setPassword(httpRequestServiceParam.get("password"));

                result = customerManager.getByFilter(customer);
                break;

            case ("update"):
                customer = new Customer();
                customer.setName(httpRequestServiceParam.get("name"));
                customer.setLogin(httpRequestServiceParam.get("login"));
                customer.setPassword(httpRequestServiceParam.get("password"));

                result = customerManager.update((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")),customer);
                break;
            case ("add"):
                customer = new Customer();
                customer.setName(httpRequestServiceParam.get("name"));
                customer.setLogin(httpRequestServiceParam.get("login"));
                customer.setPassword(httpRequestServiceParam.get("password"));

                result = customerManager.add(customer);
                break;
        }
        return result;
    }
}
