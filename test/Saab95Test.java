import org.junit.Test;

import static org.junit.Assert.*;


public class Saab95Test {
    Saab95 saab = new Saab95();

    @Test
    public void test_if_saab_subclass_of_car() {
        assertTrue(saab instanceof Car);
    }

    @Test
    public void testTurboIsFaster() {
        saab.startEngine();
        saab.gas(1);
        double noTurboSpeed = saab.getCurrentSpeed();
        saab.stopEngine();

        saab.startEngine();
        saab.setTurboOn();
        saab.gas(1);
        double turboSpeed = saab.getCurrentSpeed();

        assertTrue(turboSpeed > noTurboSpeed);
    }

    @Test
    public void testTurboOffIsSlower() {
        saab.startEngine();
        saab.setTurboOn();
        saab.gas(1);
        double turboSpeed = saab.getCurrentSpeed();
        saab.stopEngine();
        saab.setTurboOff();

        saab.startEngine();
        saab.gas(1);
        double noTurboSpeed = saab.getCurrentSpeed();

        assertTrue(noTurboSpeed < turboSpeed);
    }
}


