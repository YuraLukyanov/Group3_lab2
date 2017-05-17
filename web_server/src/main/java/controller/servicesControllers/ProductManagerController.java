package controller.servicesControllers;

import model.interfaces.services.IBusket;
import model.interfaces.services.IProductManager;
import model.pojo.Product;
import util.UtilClass;

import java.util.Map;

/**
 * Created by Nikolion on 17.05.2017.
 */
public class ProductManagerController implements StartService {

    @Override
    public Object runService(Object service, String method, Map<String, String> httpRequestServiceParam) throws Exception {
        IProductManager productManager = (IProductManager) service;
        Object result = null;
        Product product;
        switch (method) {
            case ("delete"):
                result = productManager.delete((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")));
                break;
            case ("add"):
                product = new Product();
                product.setName(httpRequestServiceParam.get("name"));
                product.setColor(httpRequestServiceParam.get("color"));
                try {
                    product.setPrice((int) UtilClass.castToType
                            (httpRequestServiceParam.get("price")));
                } catch (Exception e) {
                    product.setPrice(0);
                }
                try {
                    product.setVolume((int) UtilClass.castToType
                            (httpRequestServiceParam.get("volume")));
                } catch (Exception e) {
                    product.setVolume(0);
                }
                try {
                    product.setWeight((int) UtilClass.castToType
                            (httpRequestServiceParam.get("weight")));
                } catch (Exception e) {
                    product.setWeight(0);
                }

                result = productManager.add(product);
                break;
            case ("update"):
                product = new Product();
                product.setName(httpRequestServiceParam.get("name"));
                product.setColor(httpRequestServiceParam.get("color"));
                try {
                    product.setPrice((int) UtilClass.castToType
                            (httpRequestServiceParam.get("price")));
                } catch (Exception e) {
                    product.setPrice(0);
                }
                try {
                    product.setVolume((int) UtilClass.castToType
                            (httpRequestServiceParam.get("volume")));
                } catch (Exception e) {
                    product.setVolume(0);
                }
                try {
                    product.setWeight((int) UtilClass.castToType
                            (httpRequestServiceParam.get("weight")));
                } catch (Exception e) {
                    product.setWeight(0);
                }

                result = productManager.update((int) UtilClass.castToType
                        (httpRequestServiceParam.get("id")),product);
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
                result = productManager.getByWeight((int)UtilClass
                        .castToType(httpRequestServiceParam.get("price")));
                break;
            case ("getAll"):
                result = productManager.getAll();
                break;

        }
        return result;
    }
}
