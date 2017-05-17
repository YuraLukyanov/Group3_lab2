package controller.servicesControllers;

import java.util.Map;

/**
 * Created by Nikolion on 17.05.2017.
 */
public interface StartService {
    Object runService(Object service, String method, Map<String,String>
            httpRequestServiceParam) throws Exception;
}
