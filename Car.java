import java.awt.*;
import java.awt.geom.Point2D;

public class Car implements Movable {
    private final int nrDoors; // Number of doors on the car
    private final double enginePower; // Engine power of the car
    private double currentSpeed; // The current speed of the car
    private Color color; // Color of the car
    public String modelName; // The car model name
    private Direction direction; // The car's direction
    private Point2D.Double position;
    private boolean carInShop = false;

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

    public void setCurrentSpeed(double newSpeed) {
        if (newSpeed < 0 || newSpeed > enginePower) throw new IllegalArgumentException("New currentSpeed must be between 0 and engine power");
        currentSpeed = newSpeed;
    }

    public Color getColor(){
        return color;
    }

    public boolean isCarInShop() { return carInShop; }

    public Direction getDirection() {
        return direction;
    }

    protected void setDirection(Direction newDirection) {
        direction = newDirection;
    }

    public Point2D.Double getPosition() { return position; }

    public void setPosition(Point2D.Double newPosition) {
        position = newPosition;
    }

    public String getModelName() { return this.modelName; }

    public void changeCarInShop() { carInShop = !carInShop; }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    protected double speedFactor() {
        return enginePower * 0.01;
    }

    private void incrementSpeed(double amount){
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount,enginePower);
        currentSpeed = Math.clamp(currentSpeed, 0, enginePower);
    }
    private void decrementSpeed(double amount){
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount,0);
        currentSpeed = Math.clamp(currentSpeed, 0, enginePower);
    }

    public void gas(double amount){
        if (amount < 0 || amount > 1) throw new IllegalArgumentException("Gas amount must be between 0 and 1");
        incrementSpeed(amount);
    }

    public void brake(double amount){
        if (amount < 0 || amount > 1) throw new IllegalArgumentException("Brake amount must be between 0 and 1");
        decrementSpeed(amount);
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
            case LEFT -> setDirection(Direction.BACKWARD);
            case FORWARD -> setDirection(Direction.LEFT);
            case RIGHT -> setDirection(Direction.FORWARD);
            case BACKWARD -> setDirection(Direction.RIGHT);
        }
    }

    @Override
    public void turnRight() {
        switch (direction) {
            case LEFT -> setDirection(Direction.FORWARD);
            case FORWARD -> setDirection(Direction.RIGHT);
            case RIGHT -> setDirection(Direction.BACKWARD);
            case BACKWARD -> setDirection(Direction.LEFT);
        }
    }

    public Direction getOpositeDirection() {
        Direction opposite = null;  // This will never happen
        switch (direction) {
            case LEFT -> opposite = Direction.RIGHT;
            case FORWARD -> opposite = Direction.BACKWARD;
            case RIGHT -> opposite = Direction.LEFT;
            case BACKWARD -> opposite = Direction.FORWARD;
        }
        return opposite;
    }
}
