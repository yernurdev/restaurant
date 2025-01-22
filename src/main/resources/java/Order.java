package main.resources.java;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int counter = 0;
    private int id;
    private final Customer customer;
    private final List<MenuItem> items = new ArrayList<>();

    public Order(Customer customer) {
        this.id = ++counter;
        this.customer = customer;
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotal() {
        return items.stream().mapToDouble(MenuItem::getPrice).sum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
