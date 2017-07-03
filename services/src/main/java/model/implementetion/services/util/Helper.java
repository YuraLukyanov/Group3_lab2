package model.implementetion.services.util;

import java.util.Collection;

public class Helper {
    public static boolean isWithSameElements(Collection<?> collection1, Collection<?> collection2) {
        //null checking
        if (collection1 == null && collection2 == null)
            return true;
        if ((collection1 == null) || (collection2 == null))
            return false;

        if (collection1.size() != collection2.size())
            return false;

        for (Object itemList1 : collection1) {
            if (!collection2.contains(itemList1))
                return false;
        }

        return true;
    }
}
