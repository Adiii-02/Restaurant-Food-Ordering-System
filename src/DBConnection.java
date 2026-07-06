import java.sql.*;
import java.util.*;

public class DBConnection {
    // Database connection settings - CHANGE THESE
    private static final String URL = "jdbc:mysql://localhost:3306/restaurant_db";
    private static final String USER = "YOUR_USERNAME";  // Your MySQL username
    private static final String PASSWORD = "YOUR_PASSWORD";  // Your MySQL password
    
    // Get database connection
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new SQLException("JDBC Driver not found!");
        }
    }
    
    // Get all food items
    public static ArrayList<Food> getAllFoodItems() {
        ArrayList<Food> items = new ArrayList<>();
        String query = "SELECT * FROM food_items ORDER BY category, name";
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            
            while (rs.next()) {
                Food food = new Food(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("category"),
                    rs.getString("type")
                );
                items.add(food);
            }
            System.out.println("Loaded " + items.size() + " items from database");
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return items;
    }
    
    // Get filtered items
    public static ArrayList<Food> getFilteredItems(String category, String type) {
        ArrayList<Food> items = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM food_items WHERE 1=1");
        
        if (category != null && !category.equals("All")) {
            query.append(" AND category = '").append(category).append("'");
        }
        if (type != null && !type.equals("All")) {
            query.append(" AND type = '").append(type).append("'");
        }
        query.append(" ORDER BY name");
        
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query.toString())) {
            
            while (rs.next()) {
                Food food = new Food(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("price"),
                    rs.getString("category"),
                    rs.getString("type")
                );
                items.add(food);
            }
        } catch (SQLException e) {
            System.out.println("Filter error: " + e.getMessage());
        }
        return items;
    }
    
    // Save order
    public static int saveOrder(String customerName, int totalAmount) {
        String query = "INSERT INTO orders (customer_name, total_amount) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setString(1, customerName);
            pstmt.setInt(2, totalAmount);
            pstmt.executeUpdate();
            
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println("Save order error: " + e.getMessage());
        }
        return new Random().nextInt(10000);
    }
    
    // Debug - show all categories
    public static void debugShowAllCategories() {
        String query = "SELECT DISTINCT category, COUNT(*) as count FROM food_items GROUP BY category";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\n=== DATABASE CATEGORIES ===");
            while (rs.next()) {
                System.out.println(rs.getString("category") + ": " + rs.getInt("count") + " items");
            }
            System.out.println("==========================\n");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}