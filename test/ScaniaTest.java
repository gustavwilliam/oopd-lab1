import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ScaniaTest {
    private final Scania scania = new Scania();

    @ParameterizedTest
    @ValueSource(doubles = {0, 1, 20, 69, 70})
    public void testSetGetAllowedAngles(double angle) {
        scania.setPlatformAngle(angle);
        Assertions.assertEquals(angle, scania.getPlatformAngle());
    }
}
