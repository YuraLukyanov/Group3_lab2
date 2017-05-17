package controller.servicesControllers;

import model.interfaces.services.IOrderManager;
import model.pojo.Order;
import util.UtilClass;

import java.util.Map;

/**
 * Created by Nikolion on 17.05.2017.
 */
public class OrderManagerController implements StartService {
    @Override
    public Object runService(Object service, String method, Map<String, String> httpRequestServiceParam) throws Exception {
        IOrderManager orderManager = (IOrderManager) service;
        Object result = null;
        Order order;
        switch (method) {
            case ("get"):
                result = orderManager.get((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")));
                break;
            case ("getAll"):
                result = orderManager.getAll();
                break;
            case ("delete"):
                result = orderManager.delete((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")));
                break;
            case ("update"):
                //// TODO: 17.05.2017
                break;
            case ("add"):
                //// TODO: 17.05.2017
                break;
        }
        return result;
    }
}
