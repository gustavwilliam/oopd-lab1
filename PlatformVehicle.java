import java.awt.*;

public abstract class PlatformVehicle extends Car {
    public PlatformVehicle(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    protected abstract boolean platformClosed();

    @Override
    public void move() {
        if (platformClosed()){
            super.move();
        } else {
            throw new IllegalArgumentException("The platform is open, close before moving");
        }
    }
}
