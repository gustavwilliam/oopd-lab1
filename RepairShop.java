import java.util.ArrayList;
import java.util.List;

public class RepairShop <T extends Car> {

    private List<T> carsInShop;
    private int maxCars;
    private String name;

    public RepairShop(String name, int maxCars) {
        this.maxCars = maxCars;
        this.name = name;
        carsInShop = new ArrayList<>();
    }

    public void addCar(T object) {
        if (carsInShop.size() < maxCars && !object.isCarInShop()) {
            carsInShop.add(object);
            object.changeCarInShop();
            object.stopEngine();
        } else {
            throw new IllegalArgumentException("The shop is full, or the car is already in the shop");
        }
    }

    public void removeCar(T object){
        if (!carsInShop.isEmpty() && object.isCarInShop()){
            carsInShop.remove(object);
            object.changeCarInShop();
        } else {
            throw new IllegalArgumentException("That car is not in shop");
        }
    }

    public List<T> getCarsInShop() {
        return carsInShop;
    }
}