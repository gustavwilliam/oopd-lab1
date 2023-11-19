import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.Assert.*;

import java.awt.*;

public class CarTest {
    private final Car car = new Car(4, 100, Color.red, "Generic car");

    @Test
    public void testNrDoors() {
        assertEquals(4, car.getNrDoors());
    }

    @Test
    public void testEnginePower() {
        assertEquals(100, car.getEnginePower(), 0.1);
    }

    @Test
    public void testDefaultColor() {
        assertEquals(Color.red, car.getColor());
    }

    @Test
    public void testSetColor() {
        car.setColor(Color.cyan);
        assertEquals(Color.cyan, car.getColor());
    }

    @Test
    public void testStartingSpeed() {
        assertEquals(0, car.getCurrentSpeed(), 1e-8);
    }

    @Test
    public void testStartEngine() {
        car.startEngine();
        assertTrue(car.getCurrentSpeed() > 0);
    }

    @Test
    public void testStopEngine() {
        car.stopEngine();
        assertEquals(0, car.getCurrentSpeed(), 0.0);
    }

    @Test
    public void testGasSpeedIncrease() {
        car.startEngine();
        double oldSpeed = car.getCurrentSpeed();
        car.gas(1);
        assertTrue(car.getCurrentSpeed() > oldSpeed);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1e-5, 0.1, 0.2, 0.3, 1})
    public void testBreakingWhenSpeedIsZero(double amount) {
        car.setCurrentSpeed(0);
        car.brake(amount);
        assertEquals(0, car.getCurrentSpeed(), 0.0);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1e-5, 0.1, 0.2, 0.3, 1})
    public void testBreakSpeedDecreaseIfSpeedGreaterThanZero(double brakeAmount) {
        car.startEngine();
        double oldSpeed = car.getCurrentSpeed();
        car.brake(brakeAmount);
        assertTrue(car.getCurrentSpeed() < oldSpeed);
    }

    @ParameterizedTest
    @ValueSource(doubles = {1e-5, 0.1, 0.2, 0.3, 1})
    public void testGassingWhenSpeedIsMax(double amount) {
        car.setCurrentSpeed(car.getEnginePower());
        car.gas(amount);
        assertEquals(car.getEnginePower(), car.getCurrentSpeed(), 0.0);
    }

    @Test
    public void testDirection() {
        for (int i = 0; i < 6; i++) {
            car.turnLeft();

        }
        for (int i = 0; i < 5; i++) {
            car.turnRight();

        }
        assertEquals(Direction.LEFT, car.getDirection());
    }

    @Test
    public void testMove() {
        car.startEngine();
        car.turnLeft();
        car.turnLeft();
        car.move();
        car.turnRight();
        car.turnRight();
        car.move();
        car.move();
        car.move();

        car.turnLeft();
        car.move();
        car.turnRight();
        car.turnRight();
        car.move();
        car.move();
        car.move();
        car.move();

        assertEquals(0.3, car.getPosition().getX(), 1e-5);
        assertEquals(0.2, car.getPosition().getY(), 1e-5);
    }

    @Test
    public void testTurboIsFaster() {
        car.startEngine();
        car.gas(1);
        double noTurboSpeed = car.getCurrentSpeed();
        car.stopEngine();

        car.startEngine();
        car.setTurboOn();
        car.gas(1);
        double turboSpeed = car.getCurrentSpeed();

        assertTrue(turboSpeed > noTurboSpeed);
    }

    @Test
    public void testTurboOffIsSlower() {
        car.startEngine();
        car.setTurboOn();
        car.gas(1);
        double turboSpeed = car.getCurrentSpeed();
        car.stopEngine();
        car.setTurboOff();

        car.startEngine();
        car.gas(1);
        double noTurboSpeed = car.getCurrentSpeed();

        assertTrue(noTurboSpeed < turboSpeed);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1, 1.3, 5})
    public void testGasInputRange(double value) {
        assertThrows(IllegalArgumentException.class, () -> car.gas(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1, 1.3, 5})
    public void testBrakeInputRange(double value) {
        assertThrows(IllegalArgumentException.class, () -> car.brake(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {-1, -0.1})
    public void testCurrentSpeedNeverNegative(double value) {
        assertThrows(IllegalArgumentException.class, () -> car.setCurrentSpeed(value));
    }

    @ParameterizedTest
    @ValueSource(doubles = {1e-5, 0.1, 1, 5, 1000})
    public void testCurrentSpeedNeverGreaterThanEnginePower(double offset) {
        assertThrows(IllegalArgumentException.class, () -> car.setCurrentSpeed(car.getEnginePower() + offset));
    }
}


