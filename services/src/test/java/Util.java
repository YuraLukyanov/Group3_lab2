import model.implementetion.services.util.Helper;
import org.junit.Assert;

import java.util.Collection;

class Util {
    //assertEquals for collections without considering the order of elements
    static void assertEquals(Collection<?> expected, Collection<?> actual) {
        if (!Helper.isWithSameElements(expected, actual)) {
            Assert.fail("Not equals: \n " +
                    "expected: " + expected.toString()
                    + "\n actual: " + actual.toString());
        }
    }
}
