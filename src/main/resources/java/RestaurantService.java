package main.resources.java;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RestaurantService {
    private final Connection connection;

    public RestaurantService(Connection connection) {
        this.connection = connection;
    }

    public void addCustomer(Customer customer) {
        String query = "INSERT INTO customers (name, email) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, customer.getName());
            stmt.setString(2, customer.getEmail());
            stmt.executeUpdate();
            System.out.println("Customer added: " + customer.getName());
        } catch (SQLException e) {
            System.out.println("Error adding customer: " + e.getMessage());
        }
    }

    public void addMenuItem(MenuItem item) {
        String query = "INSERT INTO menu (name, price, type) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, item.getName());
            stmt.setDouble(2, item.getPrice());
            stmt.setString(3, item.getType());
            stmt.executeUpdate();
            System.out.println("Menu item added: " + item.getName());
        } catch (SQLException e) {
            System.out.println("Error adding menu item: " + e.getMessage());
        }
    }

    public void placeOrder(Order order) {
        String query = "INSERT INTO orders (customer_id, total) VALUES (?, ?) RETURNING id";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, order.getCustomer().getId());
            stmt.setDouble(2, order.getTotal());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                order.setId(orderId);
                addOrderItems(order);
                System.out.println("Order placed with ID: " + orderId);
            }
        } catch (SQLException e) {
            System.out.println("Error placing order: " + e.getMessage());
        }
    }

    private void addOrderItems(Order order) {
        String query = "INSERT INTO order_items (order_id, item_name, item_price) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            for (MenuItem item : order.getItems()) {
                stmt.setInt(1, order.getId());
                stmt.setString(2, item.getName());
                stmt.setDouble(3, item.getPrice());
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error adding order items: " + e.getMessage());
        }
    }

    public void listOrders() {
        String query = "SELECT * FROM orders";
        try (PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("id") + ", Total: " + rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.out.println("Error listing orders: " + e.getMessage());
        }
    }
}
