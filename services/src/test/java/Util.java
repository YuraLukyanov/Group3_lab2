import model.implementetion.services.util.Helper;
import org.junit.Assert;

import java.util.Collection;

class Util {
    //assertEquals for collections without considering the order of elements
    static void assertEquals(Collection<?> actual, Collection<?> expected) {
        if (!Helper.isWithSameElements(actual, expected)) {
            Assert.fail("Not equals: \n " +
                    "expected: " + expected.toString()
                    + "\n actual: " + actual.toString());

        }
    }
}
