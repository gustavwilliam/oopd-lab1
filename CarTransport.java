import java.awt.*;
import java.awt.geom.Point2D;
import java.util.*;

public class CarTransport extends PlatformVehicle {

    private boolean platformIsClosed;
    private int maxNrCars;
    private Stack<Car> cars = new Stack<Car>();

    public CarTransport(int nrDoors, double enginePower, Color color, String modelName, int maxNrCars) {
        super(nrDoors, enginePower, color, modelName);
        this.maxNrCars = maxNrCars;
        this.platformIsClosed = false;
        
    }

    @Override
    protected boolean platformClosed() {
        return platformIsClosed;
    }

    protected void ChangeStatusPlatform() {
        platformIsClosed = !platformIsClosed;
    }

    protected void openPlatform() {
        if (this.getCurrentSpeed() != 0) throw new IllegalArgumentException("Can't open when moving");
        platformIsClosed = false;
    }

    protected void closePlatform() {
        platformIsClosed = true;

    }

    public double distanceToPlatformBehind(Car car) {
        Direction direction = getDirection();
        Point2D.Double scaniaPosition = this.getPosition();
        Point2D.Double carPosition = car.getPosition();
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

    public double distanceToPlatformSide(Car car) {
        Direction direction = getDirection();
        Point2D.Double scaniaPosition = this.getPosition();
        Point2D.Double carPosition = car.getPosition();
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

    public int GetNumberCarsLoaded() {
        return cars.size();
    }

    public boolean CanLoadCar(Car car) {
        int conditionsPassed = 0;

        if (distanceToPlatformSide(car) < 0.2 && -0.2 < distanceToPlatformSide(car)) conditionsPassed += 1;
        if (distanceToPlatformBehind(car) < 3 && distanceToPlatformBehind(car) > 0) conditionsPassed += 1;
        if (car.getDirection() == this.getDirection() || car.getDirection() == this.getOpositeDirection()) conditionsPassed += 1;
        if (maxNrCars > GetNumberCarsLoaded() ) conditionsPassed += 1;
        if (car instanceof CarTransport) conditionsPassed -= 1;
        if (platformClosed()) conditionsPassed -= 1;

        return conditionsPassed == 4;

    }
    public void RemoveCarLastAdded(){
        Car carBeingMoved = cars.peek();
        cars.pop();

        carBeingMoved.startEngine();
        carBeingMoved.turnLeft();
        carBeingMoved.turnLeft();
        carBeingMoved.move();
        carBeingMoved.move();
    }

    public void LoadCar(Car car){
        if(CanLoadCar(car)){cars.push(car);}
    }
    @Override
    public void move() {
        super.move();
        for(Car car : cars)
        {
            car.setPosition(getPosition());
        }
    }

    @Override
    public void setDirection(Direction newDirection) {
        super.setDirection(newDirection);
        for(Car car : cars)
        {
            car.setDirection(getDirection());
        }
    }
}
