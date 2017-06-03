package controller.servicesControllers;

import controller.annotations.ControllerForService;
import controller.annotations.StartServiceMethod;
import model.interfaces.services.IProductManager;
import util.UtilClass;
import java.util.Map;
@ControllerForService(name="ProductManager")
public class ProductManagerController implements StartService {
    @StartServiceMethod
    @Override
    public Object runService(Object service, String method, Map<String, String> httpRequestServiceParam) throws Exception {
        IProductManager productManager = (IProductManager) service;
        Object result = null;
        String name;
        String color;
        int weight;
        int volume;
        int price;
        switch (method) {
            case ("delete"):
                result = productManager.delete((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")));
                break;
            case ("add"):
                name = httpRequestServiceParam.get("name");
                color = httpRequestServiceParam.get("color");
                try {
                    price = (int) UtilClass.castToType
                            (httpRequestServiceParam.get("price"));
                } catch (Exception e) {
                   price = 0;
                }
                try {
                    volume = (int) UtilClass.castToType
                            (httpRequestServiceParam.get("volume"));
                } catch (Exception e) {
                    volume = 0;
                }
                try {
                    weight = (int) UtilClass.castToType
                            (httpRequestServiceParam.get("weight"));
                } catch (Exception e) {
                    weight = 0;
                }

                result = productManager.add(name,color,weight,volume,price);
                break;
            case ("update"):
                name = httpRequestServiceParam.get("name");
                color = httpRequestServiceParam.get("color");
                try {
                    price = (int) UtilClass.castToType
                            (httpRequestServiceParam.get("price"));
                } catch (Exception e) {
                    price = 0;
                }
                try {
                    volume = (int) UtilClass.castToType
                            (httpRequestServiceParam.get("volume"));
                } catch (Exception e) {
                    volume = 0;
                }
                try {
                    weight = (int) UtilClass.castToType
                            (httpRequestServiceParam.get("weight"));
                } catch (Exception e) {
                    weight = 0;
                }


                result = productManager.update((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")),name,color,
                        weight,volume,price);
                break;
            case ("getByName"):
                result = productManager.getByName(httpRequestServiceParam.get
                        ("name"));
                break;
            case ("getByColor"):
                result = productManager.getByColor(httpRequestServiceParam.get
                        ("color"));
                break;
            case ("getByVolume"):
                result = productManager.getByVolume((int)UtilClass
                        .castToType(httpRequestServiceParam.get("volume")));
                break;
            case ("getByWeight"):
                result = productManager.getByWeight((int)UtilClass
                        .castToType(httpRequestServiceParam.get("weight")));
                break;
            case ("getByPrice"):
                result = productManager.getByPrice((int)UtilClass
                        .castToType(httpRequestServiceParam.get("price")));
                break;
            case ("getAll"):
                result = productManager.getAll();
                break;

        }
        return result;
    }
}
