import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

public class CarTransportTest {
    private final CarTransport carT =
            new CarTransport(4, 100, Color.black, "Volvo240", 4);

    @Test
    public void testPlatformInitiallyClosed() {
        assertFalse(carT.platformClosed());
    }

    @Test
    public void testChangePlatformStatus() {
        carT.changeStatusPlatform();
        assertTrue(carT.platformClosed());
    }

    @Test
    public void testLoadCar() {
        Volvo240 volvo240 = new Volvo240();
        volvo240.startEngine();
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.move();

        carT.openPlatform();
        carT.LoadCar(volvo240);
        assertEquals(1, carT.getNumberCarsLoaded());
    }

    @Test
    public void testRemoveCarLastAdded(){
        Volvo240 volvo240 = new Volvo240();
        volvo240.startEngine();
        volvo240.turnLeft();
        volvo240.turnLeft();
        volvo240.move();
        carT.LoadCar(volvo240);
        carT.removeCarLastAdded();
        carT.openPlatform();
        assertEquals(0, carT.getNumberCarsLoaded());
    }
}
