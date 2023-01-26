package product;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductManagerTest {

    @Test
    public void testProductManagerAddProduct() {
        ProductManager productManager = new ProductManager();

        productManager.addProduct("book", 5.99);
        List<Product> products = new ArrayList<>();
        Product product = new Product("book", 5.99);
        products.add(product);
        assertEquals(products, productManager.getProducts());

        productManager.addProduct("", 4.59);
        assertEquals(products, productManager.getProducts());
    }

    @Test
    public void testProductManagerGetAllProducts() {
        ProductManager productManager = new ProductManager();
        List<Product> products = new ArrayList<>();
        products.add(new Product("ruler", 0.99));
        products.add(new Product("notebook", 1.99));
        List<Product> products_ = new ArrayList<>(products);

        productManager.addProduct("ruler", 0.99);
        productManager.addProduct("notebook", 1.99);
        productManager.addProduct("", 6.99);
        assertEquals(products_, productManager.getProducts());
    }

    @Test
    public void testProductManagerRemoveAllProduct() {
        ProductManager productManager = new ProductManager();

        productManager.addProduct("pen", 2.99);
        productManager.addProduct("eraser", 1.98);
        productManager.addProduct("", 9.99);
        productManager.removeAllProducts();
        assertEquals(true, productManager.getProducts().isEmpty());
    }
}
