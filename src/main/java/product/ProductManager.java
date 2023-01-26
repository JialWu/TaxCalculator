package product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProductManager {
    private List<Product> products = new ArrayList<>();
    public boolean containsName(String name) {
        return products.stream().anyMatch(product -> product.getName().equals(name));
    }
    public void addProduct(String name, double gross) {
        if (name != null && !name.trim().isEmpty()) {
            if (containsName(name)) {
                products.removeIf(product -> product.getName().equals(name));
            }
            products.add(new Product(name, gross));
        }
    }
    public List<Product> getProducts() {
        return  new ArrayList<>(products);
    }
    public void removeAllProducts() {
        products.clear();
    }
}
