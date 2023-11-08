import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
        assertEquals(125, saab95.getEnginePower(), 0.1);
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
        assertEquals(0, saab95.getCurrentSpeed(), 1e-8);
    }

    @Test
    public void testStartEngine() {
        saab95.startEngine();
        assertTrue(saab95.getCurrentSpeed() > 0);
    }

    @Test
    public void testStopEngine() {
        saab95.stopEngine();
        assertEquals(0, saab95.getCurrentSpeed(), 0.0);
    }

    @Test
    public void testGasSpeedIncrease() {
        saab95.startEngine();
        double oldSpeed = saab95.getCurrentSpeed();
        saab95.gas(1);
        assertTrue(saab95.getCurrentSpeed() > oldSpeed);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1e-5, 0.1, 0.2, 0.3, 1})
    public void testBreakingWhenSpeedIsZero(double amount) {
        saab95.setCurrentSpeed(0);
        saab95.brake(amount);
        assertEquals(0, saab95.getCurrentSpeed(), 0.0);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1e-5, 0.1, 0.2, 0.3, 1})
    public void testBreakSpeedDecreaseIfSpeedGreaterThanZero(double brakeAmount) {
        saab95.startEngine();
        double oldSpeed = saab95.getCurrentSpeed();
        saab95.brake(brakeAmount);
        assertTrue(saab95.getCurrentSpeed() < oldSpeed);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1e-5, 0.1, 0.2, 0.3, 1})
    public void testGassingWhenSpeedIsMax(double amount) {
        saab95.setCurrentSpeed(saab95.getEnginePower());
        saab95.gas(amount);
        assertEquals(saab95.getEnginePower(), saab95.getCurrentSpeed(), 0.0);
    }

    @Test
    public void testDirection() {
        for (int i = 0; i < 6; i++) {
            saab95.turnLeft();

        }
        for (int i = 0; i < 5; i++) {
            saab95.turnRight();

        }
        assertEquals(Direction.LEFT, saab95.getDirection());
    }

    @Test
    public void testMove() {
        saab95.startEngine();
        saab95.turnLeft();
        saab95.turnLeft();
        saab95.move();
        saab95.turnRight();
        saab95.turnRight();
        saab95.move();
        saab95.move();
        saab95.move();

        saab95.turnLeft();
        saab95.move();
        saab95.turnRight();
        saab95.turnRight();
        saab95.move();
        saab95.move();
        saab95.move();
        saab95.move();

        assertEquals(0.3, saab95.getPosition().getX(), 1e-5);
        assertEquals(0.2, saab95.getPosition().getY(), 1e-5);
    }

    @Test
    public void testTurboIsFaster() {
        saab95.startEngine();
        saab95.gas(1);
        double noTurboSpeed = saab95.getCurrentSpeed();
        saab95.stopEngine();

        saab95.startEngine();
        saab95.setTurboOn();
        saab95.gas(1);
        double turboSpeed = saab95.getCurrentSpeed();

        assertTrue(turboSpeed > noTurboSpeed);
    }

    @Test
    public void testTurboOffIsSlower() {
        saab95.startEngine();
        saab95.setTurboOn();
        saab95.gas(1);
        double turboSpeed = saab95.getCurrentSpeed();
        saab95.stopEngine();
        saab95.setTurboOff();

        saab95.startEngine();
        saab95.gas(1);
        double noTurboSpeed = saab95.getCurrentSpeed();

        assertTrue(noTurboSpeed < turboSpeed);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1, 1.3, 5})
    public void testGasInputRange(double value) {
        assertThrows(IllegalArgumentException.class, () -> saab95.gas(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1, 1.3, 5})
    public void testBrakeInputRange(double value) {
        assertThrows(IllegalArgumentException.class, () -> saab95.brake(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1})
    public void testCurrentSpeedNeverNegative(double value) {
        assertThrows(IllegalArgumentException.class, () -> saab95.setCurrentSpeed(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1e-5, 0.1, 1, 5, 1000})
    public void testCurrentSpeedNeverGreaterThanEnginePower(double offset) {
        assertThrows(IllegalArgumentException.class, () -> saab95.setCurrentSpeed(saab95.getEnginePower() + offset));
    }
}


