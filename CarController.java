import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Car> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        cc.cars.add(new Volvo240());
        cc.cars.add(new Saab95());

        setStartPositions(cc);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Car car : cars) {
                if(car.getCurrentSpeed() != 0){
                    if(car.getNextPosition().getY() < 0){
                        atWallTurn(car);
                        car.setPosition(car.getPosition().x, 0);
                    }
                    if(car.getNextPosition().getY() > frame.getMaxY() - (frame.controlPanel.getSize().height + 200)){
                        atWallTurn(car);
                        car.setPosition(car.getPosition().x,frame.getMaxY() - (frame.controlPanel.getSize().height + 200) );
                    }
                }
                car.move();
                int x = (int) Math.round(car.getPosition().getX());
                int y = (int) Math.round(car.getPosition().getY());
                frame.drawPanel.moveit(car,x, y);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.gas(gas);
        }
    }

    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (Car car : cars
        ) {
            car.brake(brake);
        }
    }

    void stop() {
        for (Car car : cars
        ) {
            car.setCurrentSpeed(0);
            car.stopEngine();
        }
    }

    void start() {
        for (Car car : cars
        ) {
            car.startEngine();
        }
    }

    void atWallTurn(Car car) {
        car.turnLeft();
        car.turnLeft();
        car.setCurrentSpeed(0);

    }

    public static void setStartPositions(CarController carC){
        double x = 0;
        for (Car car : carC.cars) {
            car.setPosition((x,0));
            x += 100;
        }
    }
}


