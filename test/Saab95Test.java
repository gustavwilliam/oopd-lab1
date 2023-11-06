import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.awt.*;

public class Saab95Test {
    private final Saab95 saab95 = new Saab95();

    @Test
    public void testNrDoors() {
        Assertions.assertEquals(2, saab95.getNrDoors());
    }

    @Test
    public void testEnginePower() {
        Assertions.assertEquals(125, saab95.getEnginePower());
    }

    @Test
    public void testDefaultColor() {
        Assertions.assertEquals(Color.red, saab95.getColor());
    }

    @Test
    public void testSetColor() {
        saab95.setColor(Color.cyan);
        Assertions.assertEquals(Color.cyan, saab95.getColor());
    }

    @Test
    public void testStartingSpeed() {
        Assertions.assertEquals(0, saab95.getCurrentSpeed());
    }

    @Test
    public void startEngine() {
        saab95.startEngine();

    }

    @Test
    public void stopEngine() {

    }

    public void testSpeedIncrease(){
        ogSpeed = saab95.getCurrentSpeed();
        newSpeed =


    }
}
