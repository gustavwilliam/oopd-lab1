import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

public class Scania extends PlatformVehicle  {

    private double platformAngle;
    private Stack<Car> cars = new Stack<Car>();

    public Scania() {
        super(2, 100, Color.blue, "Scania");
        this.platformAngle = 0;
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
    protected boolean platformClosed(){
        return platformAngle == 0;
    }


}