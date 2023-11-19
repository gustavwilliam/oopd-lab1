import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

public class CarTransport extends PlatformVehicle {

    private boolean platformIsClosed;
    private Stack<Car> cars = new Stack<Car>();

    public CarTransport(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    @Override
    protected boolean platformClosed() {
        return platformIsClosed;
    }

    private void RemoveItemLastAdded(){
        Car carBeingMoved = cars.peek();
        cars.pop();
        //Get position of the truck and then make car change position to behind the truck
    }

    public double distanceToPlatformBehind() {
        Direction direction = getDirection();
        Point2D.Double scaniaPosition = this.getPosition();
        Point2D.Double carPosition = this.getPosition();
        double distanceBehind = 0;

        switch (direction) {
            case LEFT -> {
                distanceBehind = carPosition.x - scaniaPosition.x;
            }
            case FORWARD -> {
                distanceBehind = scaniaPosition.y - carPosition.y;
            }
            case RIGHT -> {
                distanceBehind = scaniaPosition.x - carPosition.x;
            }
            case BACKWARD -> {
                distanceBehind = carPosition.y - scaniaPosition.y;
            }
        }
        return distanceBehind;
    }

    public double distanceToPlatformSide() {
        Direction direction = getDirection();
        Point2D.Double scaniaPosition = this.getPosition();
        Point2D.Double carPosition = this.getPosition();
        double distanceSide = 0;

        switch (direction) {
            case LEFT, RIGHT -> {
                distanceSide = carPosition.y - scaniaPosition.y;
            }
            case FORWARD, BACKWARD -> {
                distanceSide = scaniaPosition.x - carPosition.x;
            }
        }
        return distanceSide;
    }

    public boolean CanAddCar(Car car) {
        int conditionsPassed = 0;
        // satte bara i några random värden
        if(distanceToPlatformSide() < 0.2 && distanceToPlatformBehind() < 1) {conditionsPassed += 1; }

        if (car.getDirection() == this.getDirection() || car.getDirection() == this.getOpositeDirection()){conditionsPassed += 1; }

        //if (conditionsPassed) {  }
        return false;

    }

    public void AddCar(Car car) {
        cars.push(car);
    }

    public void RemoveItem() {

    }
}

