package main.resources.java;

public class Food extends MenuItem {
    public Food(String name, double price) {
        super(name, price);
    }

    @Override
    public String getType() {
        return "Food";
    }
}
