import org.junit.Test;
import static org.junit.Assert.*;

import java.awt.*;

public class Saab95Test {
    private final Saab95 saab95 = new Saab95();

    @Test
    public void testNrDoors() {

        assertEquals(2, saab95.getNrDoors());
    }

    @Test
    public void testEnginePower() {
        assertEquals(125, saab95.getEnginePower());
    }

    @Test
    public void testDefaultColor() {
        assertEquals(Color.red, saab95.getColor());
    }

    @Test
    public void testSetColor() {
        saab95.setColor(Color.cyan);
        assertEquals(Color.cyan, saab95.getColor());
    }

    @Test
    public void testStartingSpeed() {
        assertEquals(0, saab95.getCurrentSpeed());
    }

    @Test
    public void startEngine() {
        saab95.startEngine();

    }

    @Test
    public void stopEngine() {

    }

    @Test
    public void testGasSpeedIncrease() {
        double oldSpeed = saab95.getCurrentSpeed();
        saab95.gas(1);
        assertTrue(saab95.getCurrentSpeed() > oldSpeed);
    }

    @Test
    public void testBreakSpeedDecrease() {
        double oldSpeed = saab95.getCurrentSpeed();
        saab95.brake(1);
        assertTrue(saab95.getCurrentSpeed() > oldSpeed);
    }

    @Test
    public void testMoveDirection(){
        for (int i = 0; i < 4; i++) {
            saab95.move();
            saab95.turnLeft();

        }
        for (int i = 0; i < 4; i++) {
            saab95.move();
            saab95.turnRight();
        }
    }
}


