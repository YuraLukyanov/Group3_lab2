package controller.servicesControllers;

import model.interfaces.services.IAuthorization;
import java.util.Map;

/**
 * Created by Nikolion on 17.05.2017.
 */
public class AuthorizationController implements StartService {
    @Override
    public Object runService(Object service, String method, Map<String, String> httpRequestServiceParam) throws Exception {
        IAuthorization authorization = (IAuthorization) service;
        Object result = null;
        switch (method) {
            case ("getCustomer"):
                result = authorization.getCustomer();
                break;
        }
        return result;
    }
}
