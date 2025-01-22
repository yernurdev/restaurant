package main.resources.java;

public class Drink extends MenuItem {
    public Drink(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "Drink";
    }
}
