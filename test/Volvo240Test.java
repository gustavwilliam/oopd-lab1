import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;

public class Volvo240Test {
    private final Volvo240 volvo240 = new Volvo240();

    @Test
    public void testNrDoors() {
        assertEquals(4, volvo240.getNrDoors());
    }

    @Test
    public void testEnginePower() {
        assertEquals(100, volvo240.getEnginePower());
    }

    @Test
    public void testDefaultColor() {
        assertEquals(Color.black, volvo240.getColor());
    }

    @Test
    public void testSetColor() {
        volvo240.setColor(Color.cyan);
        assertEquals(Color.cyan, volvo240.getColor());
    }

    @Test
    public void testModelName() {
        assertEquals("Volvo240", volvo240.modelName);
    }

    @Test
    public void testModelName() {
        assertEquals("Volvo240", volvo240.modelName);
    }

    @Test
    public void incrementSpeed() {
        volvo240.incrementSpeed(10);
        assertEquals();
    }
}
