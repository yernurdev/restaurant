package main.resources.java;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.connect();

        if (connection != null) {
            RestaurantService service = new RestaurantService(connection);

            Customer customer = new Customer("John Doe", "john@example.com");
            service.addCustomer(customer);

            Food pizza = new Food("Pizza", 12.99);
            Drink cola = new Drink("Cola", 1.99);
            service.addMenuItem(pizza);
            service.addMenuItem(cola);

            Order order = new Order(customer);
            order.addItem(pizza);
            order.addItem(cola);
            service.placeOrder(order);

            service.listOrders();
        } else {
            System.out.println("Failed to connect to the database. Exiting application.");
        }
    }
}