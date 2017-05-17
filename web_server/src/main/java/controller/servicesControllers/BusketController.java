package controller.servicesControllers;

import model.interfaces.services.IBusket;
import util.UtilClass;
import java.util.Map;

/**
 * Created by Nikolion on 17.05.2017.
 */
public class BusketController {

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
        }
        return result;
    }
}
