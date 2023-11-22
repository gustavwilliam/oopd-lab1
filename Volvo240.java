import jdk.jshell.spi.ExecutionControl;

import java.awt.*;

public class Volvo240 extends Car {
    private final double trimFactor;

    public Volvo240() {
        super(4, 100, Color.black, "Volvo240");
        trimFactor = 1.25;
    }

    @Override
    protected double speedFactor() {
        return getEnginePower() * 0.01 * trimFactor;
    }
}
