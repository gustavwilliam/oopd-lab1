import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.Test;
import static org.junit.Assert.*;

public class ScaniaTest {
    private final Scania scania = new Scania();

    @ParameterizedTest
    @ValueSource(doubles = {0, 1, 20, 69, 70})
    public void testSetGetAllowedAngles(double angle) {
        scania.setPlatformAngle(angle);
        Assertions.assertEquals(angle, scania.getPlatformAngle());
    }

    @Test
    public void test_if_scania_subclass_of_PlatformVehicle() {
        Scania scania = new Scania();
        assertTrue(scania instanceof PlatformVehicle);
    }
}
