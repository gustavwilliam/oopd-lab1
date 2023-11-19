import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;

public class Volvo240Test {
    @Test
    public void test_if_volvo_subclass_of_Car() {
        Volvo240 volvo = new Volvo240();
        assertTrue(volvo instanceof Car);
    }
}
