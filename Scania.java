import java.awt.*;

public class Scania extends PlatformVehicle {

    // TODO: Add at least one test for this class
    private double platformAngle;

    public Scania() {
        super(2, 100, Color.blue, "Scania");
        this.platformAngle = 0;
    }

    private double speedFactor() {
        return getEnginePower() * 0.01;
    }

    public double getPlatformAngle() {
        return platformAngle;
    }

    public void setPlatformAngle(double platformAngle) {
        if (platformAngle < 0 || platformAngle > 70)
            throw new IllegalArgumentException("platformAngle can't be more than 70 or less than 0");
        if (getCurrentSpeed() != 0) {
            throw new IllegalArgumentException("platformAngle can't move during car movement");
        }
        this.platformAngle = platformAngle;
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

    @Override
    protected boolean platformClosed(){
        return platformAngle == 0;
    }
}