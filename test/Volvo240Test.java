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
        assertEquals(100, volvo240.getEnginePower(), 1e-8);
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
    public void testIncrementSpeed() {
        double speedFactor = volvo240.getEnginePower() * 0.01 * 1.25;
        double initialSpeed = volvo240.getCurrentSpeed();
        double amount = 10;
        volvo240.incrementSpeed(amount);
        assertEquals(initialSpeed + speedFactor * amount, volvo240.getCurrentSpeed(), 1e-8);
    }

    @Test
    public void testDecreaseSpeed() {
        double initialSpeed = 30;
        volvo240.currentSpeed = initialSpeed;
        double speedFactor = volvo240.getEnginePower() * 0.01 * 1.25;
        double amount = 10;
        volvo240.decreaseSpeed(amount);
        assertEquals(Math.max(initialSpeed - speedFactor * amount, 0), volvo240.getCurrentSpeed(), 1e-8);
    }
}
