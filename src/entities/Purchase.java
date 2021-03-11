package entities;

public class Purchase {

    private Product product;

    private int quantity;

    private double sum;

    public Purchase(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.sum = quantity * product.getUnit_value();
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSum() {
        return sum;
    }
}
