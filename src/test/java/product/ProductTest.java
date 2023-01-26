package product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    @Test
    public void testProductConstructor() {
        Product product1 = new Product("pencil", 1.99);
        assertEquals("pencil,1.99,0.32,1.67", product1.toString());
        Product product2 = new Product("pencil", 0);
        assertEquals("pencil,0.00,0.00,0.00", product2.toString());
    }

    @Test
    public void testProductGetName() {
        Product product = new Product("pencil case", 2.99);
        assertEquals("pencil case", product.getName());
    }
}
