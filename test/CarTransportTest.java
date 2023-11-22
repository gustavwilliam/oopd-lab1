import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class CarTransportTest {
    private final CarTransport carT = new CarTransport(4, 100, Color.black, "Volvo240", 4);

    @Test
    public void testPlatformInitiallyClosed() {
        assertEquals(true, carT.platformClosed());
    }

    @Test
    public void testChangePlatformStatus() {
        carT.ChangeStatusPlatform();
        assertEquals(false, carT.platformClosed());
    }

    @Test
    public void testAddCar() {
        Volvo240 bil1 = new Volvo240();
        carT.AddCar(bil1);
        assertEquals(false, carT.platformClosed());
    }

}
