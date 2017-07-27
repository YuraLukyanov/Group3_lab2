package controller.services;

import controller.annotations.ControllerForService;
import controller.annotations.StartServiceMethod;
import model.interfaces.services.IOrderManager;
import util.UtilClass;

import java.util.Map;

@ControllerForService(name="OrderManager")
public class OrderManagerController implements StartService {
    @StartServiceMethod
    @Override
    public Object runService(Object service, String method, Map<String, String> httpRequestServiceParam) throws Exception {
        IOrderManager orderManager = (IOrderManager) service;
        Object result = null;
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
            case ("add"):
                result = orderManager.add();
                break;
            default: /*NOP*/
        }
        return result;
    }
}
