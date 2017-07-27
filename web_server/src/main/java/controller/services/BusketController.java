package controller.services;

import controller.annotations.ControllerForService;
import controller.annotations.StartServiceMethod;
import model.interfaces.services.IBusket;
import util.UtilClass;
import java.util.Map;

@ControllerForService(name="Busket")
public class BusketController implements StartService {

    @StartServiceMethod
    public Object runService(Object service, String method, Map<String,String>
            httpRequestServiceParam) throws Exception {
        IBusket busket = (IBusket) service;
        Object result = null;
        switch (method){
            case ("add"):
                result= busket.add((int)UtilClass.castToType
                        (httpRequestServiceParam.get("id")));
                break;
            case ("delete"):
                result= busket.delete((int)UtilClass.castToType
                        (httpRequestServiceParam.get("index")));
                break;
            case ("setAmount"):
                result= busket.setAmount((int)UtilClass.castToType
                        (httpRequestServiceParam.get("index")),(int)UtilClass.castToType
                        (httpRequestServiceParam.get("newAmount")));
                break;
            case ("getProductsAndAmounts"):
                result= busket.getProductsAndAmounts();
                break;
            case ("getOrder"):
                result= busket.getOrder();
                break;
            case ("clear"):
                result= busket.clear();
                break;
            default: /*NOP*/
        }
        return result;
    }
}
