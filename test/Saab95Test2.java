import junit.framework.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class Volvo240Test {
    private final Volvo240 volvo240 = new Volvo240();

    @Test
    public void testNrDoors() {
        Assertions.assertEquals(4, volvo240.getNrDoors());
    }

    @Test
    public void testEnginePower() {
        Assertions.assertEquals(100, volvo240.getEnginePower());
    }

    @Test
    public void testDefaultColor() {
        Assertions.assertEquals(Color.black, volvo240.getColor());
    }

    @Test
    public void testSetColor() {
        volvo240.setColor(Color.cyan);
        Assertions.assertEquals(Color.cyan, volvo240.getColor());
    }

    @Test
    public void testModelName() {
        Assertions.assertEquals("Volvo240", volvo240.modelName);
    }

    @Test
    public void testModelName() {
        Assertions.assertEquals("Volvo240", volvo240.modelName);
    }
}
