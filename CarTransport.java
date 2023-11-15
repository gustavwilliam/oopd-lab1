import java.awt.*;

public class CarTransport extends PlatformVehicle {

    private boolean platformIsClosed;
    //private Car cars[];

    public CarTransport(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);

    }

    @Override
    protected boolean platformClosed() {
        return platformIsClosed;
    }

    @Override
    protected void incrementSpeed(double amount) {
        if (platformClosed()) {
            // Car can only drive if platform is closed
            setCurrentSpeed(Math.min(getCurrentSpeed() + speedFactor() * amount, getEnginePower()));
        }
    }

    @Override
    protected void decreaseSpeed(double amount) {
        setCurrentSpeed(Math.max(getCurrentSpeed() - speedFactor() * amount, 0));
    }

    private double speedFactor() {
        return getEnginePower() * 0.01;
    }



}

