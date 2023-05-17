package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import main.business.ProductManager;
import main.exception.NegativeNumberException;
import main.model.ProductProportion;

public class ProductTest {
    private ProductManager productManager;

    @org.junit.jupiter.api.BeforeEach
    public void setUp() {
        productManager = new ProductManager();
    }

    @org.junit.jupiter.api.Test
    public void calculateProductProportion() throws NegativeNumberException {
        assertEquals(0.5, productManager.calculateProductProportion(new ProductProportion("coca", 50), 100), 0.01);
        assertEquals(0.0, productManager.calculateProductProportion(new ProductProportion("fanta", 0), 0), 0.01);
        assertEquals(1.0, productManager.calculateProductProportion(new ProductProportion("sprite", 100), 100), 0.01);
    }

    @org.junit.jupiter.api.Test
    public void calculateProductProportionException() {
        assertThrows(NegativeNumberException.class, () -> {
            productManager.calculateProductProportion(new ProductProportion("ice-tea", -50), 100);
        });

        assertThrows(NegativeNumberException.class, () -> {
            productManager.calculateProductProportion(new ProductProportion("dr pepper", 50), -100);
        });
    }
}
