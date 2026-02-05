package csd214.bookstore.mysql;
import csd214.bookstore.pojos.Pen;
import java.sql.*;
import java.util.UUID;
public class JdbcPenApp {
    private static final String URL = "jdbc:mysql://localhost:3307/bookstore";
    private static final String USER = "csd214";
    private static final String PASS = "itstudies12345";
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // 1. Create Table
            createTable(conn);
            // 2. Insert
            System.out.println("--- INSERTING ---");
            Pen p1 = new Pen("Pilot", 6.8, 100, "Red");
            String id = p1.getProductId();
            insertPen(conn, p1);
            // 3. Read
            System.out.println("--- READING ---");
            listPens(conn);
            // 4. Update
            System.out.println("--- UPDATING ---");
            updatePenPrice(conn, id, 25.50);

            // 5. Delete
            System.out.println("--- DELETING ---");
            deletePen(conn, id);
            listPens(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS pens (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(36), " +
                "pen_brand VARCHAR(255), " +
                "pen_price DOUBLE, " +
                "pen_quantity INTEGER, " +
                "pen_color VARCHAR(100));";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'pens' ready.");
        }
    }
    private static void insertPen(Connection conn, Pen w) throws SQLException {
        // SECURITY: Use ? to prevent SQL Injection
        String sql = "INSERT INTO pens (product_id, pen_brand, pen_price, pen_quantity, pen_color) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, w.getProductId()); // UUID
            ps.setString(2, w.getBrand());
            ps.setDouble(3, w.getPrice());
            ps.setInt(4, w.getQuantity());
            ps.setString(5, w.getColor());
            ps.executeUpdate();
            System.out.println("Saved: " + w.getBrand());
        }
    }
    private static void listPens(Connection conn) throws SQLException {
        String sql = "SELECT * FROM pens";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | UUID: %s | Brand: %s | Price: $%.2f%n | Quantity: %d | Color: %s\n",
                        rs.getInt("id"),
                        rs.getString("product_id"),
                        rs.getString("pen_brand"),
                        rs.getDouble("pen_price"),
                        rs.getInt("pen_quantity"),
                        rs.getString("pen_color"));
            }
        }
    }
    private static void updatePenPrice(Connection conn, String id, double newPrice) throws SQLException {
        String sql = "UPDATE pens SET pen_price = ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, id);
            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " pen(s).");
        }
    }

    private static void deletePen(Connection conn, String id) throws SQLException {
        String sql = "DELETE FROM pens WHERE product_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Deleted pen: " + id);
        }
    }
}