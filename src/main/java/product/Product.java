package product;

public class Product {
    private String name;
    private double gross;
    public Product(String name, double gross) {
        this.name = name;
        this.gross = gross;
    }
    private double CalculateNet() {
        return this.gross / 1.19;
    }
    private double CalculateTax() {
        return this.gross - CalculateNet();
    }
    public String getName() {
        return this.name;
    }
    @Override
    public String toString() {
        return name + ',' +
                String.format("%.2f", gross) + ',' +
                String.format("%.2f", CalculateTax()) + ',' +
                String.format("%.2f", CalculateNet());
    }
    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        if (other.getClass() != this.getClass()) {
            return false;
        }

        Product otherProduct = (Product) other;

        if (!this.name.equals(otherProduct.name)) {
            return false;
        }

        if (this.gross != otherProduct.gross) {
            return false;
        }
        return true;
    }
}
