import java.awt.*;

public abstract class PlatformVehicle extends Car {
    public PlatformVehicle(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    protected abstract boolean platformClosed();

    protected abstract void incrementSpeed(double amount);

    protected abstract void decreaseSpeed(double amount);
}
