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
        carT.ChangeStatusPlatform();
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
        assertEquals(1, carT.GetNumberCarsLoaded());
    }
}
