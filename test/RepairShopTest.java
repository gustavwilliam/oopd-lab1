import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;
import org.junit.jupiter.api.function.Executable;

public class RepairShopTest {

    @Test
    public void test_addCar(){
        RepairShop<Car> Repairshop = new RepairShop<>("testshop", 1);
        Volvo240 volvo240 = new Volvo240();
        Repairshop.addCar(volvo240);
        assertTrue(volvo240.isCarInShop());
    }

    @Test
    public void test_addCarVolvo(){
        RepairShop<Volvo240> Repairshop = new RepairShop<>("testshop", 1);
        Volvo240 volvo240 = new Volvo240();
        Repairshop.addCar(volvo240);
        assertTrue(volvo240.isCarInShop());
    }

    @Test
    public void test_if_addCar_throws_exception() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Volvo240 volvo240 = new Volvo240();
                RepairShop<Car> Repairshop = new RepairShop<>("BertilsShop", 0);
                Repairshop.addCar(volvo240);
            }
        });
    }

    @Test
    public void test_removeCar(){
        RepairShop<Car> Repairshop = new RepairShop<>("testshop", 1);
        Volvo240 volvo240 = new Volvo240();
        Repairshop.addCar(volvo240);
        assertTrue(volvo240.isCarInShop());
        Repairshop.removeCar(volvo240);
        assertFalse(volvo240.isCarInShop());

    }

    @Test
    public void test_if_removeCar_throws_exception() {
        assertThrows(IllegalArgumentException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Volvo240 volvo240 = new Volvo240();
                RepairShop<Car> Repairshop = new RepairShop<>("BertillsShop", 1);
                Repairshop.removeCar(volvo240);
            }
        });
    }
}