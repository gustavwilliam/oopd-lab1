import org.junit.Test;

import static org.junit.Assert.*;


public class Saab95Test {
    @Test
    public void test_if_saab_subclass_of_Car() {
        Saab95 saab = new Saab95();
        assertTrue(saab instanceof Car);
    }
}


