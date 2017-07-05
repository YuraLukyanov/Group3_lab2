package controller.servicesControllers;

import controller.annotations.ControllerForService;
import controller.annotations.StartServiceMethod;
import model.interfaces.services.IAuthorization;
import java.util.Map;

@ControllerForService(name="Authorization")
public class AuthorizationController implements StartService {
    @StartServiceMethod
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
