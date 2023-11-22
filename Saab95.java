import java.awt.*;

public class Saab95 extends Car {
    private boolean turboOn = false;
    private final double turboFactor;

    public Saab95(){
        super(2, 125, Color.red, "Saab95");
        turboFactor = 1.3;
    }

    public void setTurboOn(){
        turboOn = true;
    }

    public void setTurboOff(){
        turboOn = false;
    }

    @Override
    protected double speedFactor() {
        double turboScale = turboOn ? turboFactor : 1;
        return getEnginePower() * 0.01 * turboScale;
    }
}