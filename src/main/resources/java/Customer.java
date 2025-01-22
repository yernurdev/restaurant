package main.resources.java;

public class Customer {
    private static int counter = 0;
    private final int id;
    private final String name;
    private final String email;

    public Customer(String name, String email) {
        this.id = ++counter;
        this.name = name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
