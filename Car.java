import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Car implements Movable {
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    protected double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    public String modelName; // The car model name
    private Direction direction; // The car's direction
    private final Point2D.Double position;

    public Car(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.color = color;
        this.enginePower = enginePower;
        this.modelName = modelName;
        this.direction = Direction.FORWARD;
        this.position = new Point2D.Double(0, 0);
        stopEngine();
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    protected abstract void incrementSpeed(double amount);

    protected abstract void decreaseSpeed(double amount);

    public void gas(double amount){
        incrementSpeed(amount);
    }

    public void brake(double amount){
        decreaseSpeed(amount);
    }

    private Point2D.Double getNextPosition() {
        return switch (direction) {
            case LEFT -> new Point2D.Double(position.getX() - getCurrentSpeed(), position.getY());
            case FORWARD -> new Point2D.Double(position.getX(), position.getY() + getCurrentSpeed());
            case RIGHT -> new Point2D.Double(position.getX() + getCurrentSpeed(), position.getY());
            case BACKWARD -> new Point2D.Double(position.getX(), position.getY() - getCurrentSpeed());
        };
    }

    @Override
    public void move() {
        position.setLocation(getNextPosition());
    }

    @Override
    public void turnLeft() {
        switch (direction) {
            case LEFT -> direction = Direction.BACKWARD;
            case FORWARD -> direction = Direction.LEFT;
            case RIGHT -> direction = Direction.FORWARD;
            case BACKWARD -> direction = Direction.RIGHT;
        }
    }

    @Override
    public void turnRight() {
        switch (direction) {
            case LEFT -> direction = Direction.FORWARD;
            case FORWARD -> direction = Direction.RIGHT;
            case RIGHT -> direction = Direction.BACKWARD;
            case BACKWARD -> direction = Direction.LEFT;
        }
    }
}
