package entities;

public class Product {

    private String name;

    private double unit_value;

    public Product(String name, double unit_value) {
        this.name = name;
        this.unit_value = unit_value;
    }

    public String getName() {
        return name;
    }

    public double getUnit_value() {
        return unit_value;
    }
}
