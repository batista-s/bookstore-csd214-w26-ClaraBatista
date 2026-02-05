package csd214.bookstore.mysql;
import csd214.bookstore.pojos.Notebook;
import java.sql.*;
import java.util.UUID;
public class JdbcNotebookApp {
    private static final String URL = "jdbc:mysql://localhost:3307/bookstore";
    private static final String USER = "csd214";
    private static final String PASS = "itstudies12345";
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // 1. Create Table
            createTable(conn);
            // 2. Insert
            System.out.println("--- INSERTING ---");
            Notebook p1 = new Notebook("Tilibra", 10.99, 100, 80);
            String id = p1.getProductId();
            insertNotebook(conn, p1);
            // 3. Read
            System.out.println("--- READING ---");
            listNotebooks(conn);
            // 4. Update
            System.out.println("--- UPDATING ---");
            updateNotebookPrice(conn, id, 25.50);

            // 5. Delete
            System.out.println("--- DELETING ---");
            deleteNotebook(conn, id);
            listNotebooks(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS notebooks (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(36), " +
                "notebook_brand VARCHAR(255), " +
                "notebook_price DOUBLE, " +
                "notebook_quantity INTEGER, " +
                "notebook_pageCount VARCHAR(100));";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'notebooks' ready.");
        }
    }
    private static void insertNotebook(Connection conn, Notebook w) throws SQLException {
        // SECURITY: Use ? to prevent SQL Injection
        String sql = "INSERT INTO notebooks (product_id, notebook_brand, notebook_price, notebook_quantity, notebook_pageCount) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, w.getProductId()); // UUID
            ps.setString(2, w.getBrand());
            ps.setDouble(3, w.getPrice());
            ps.setInt(4, w.getQuantity());
            ps.setInt(5, w.getPageCount());
            ps.executeUpdate();
            System.out.println("Saved: " + w.getBrand());
        }
    }
    private static void listNotebooks(Connection conn) throws SQLException {
        String sql = "SELECT * FROM notebooks";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | UUID: %s | Brand: %s | Price: $%.2f%n | Quantity: %d | PageCount: %d\n",
                        rs.getInt("id"),
                        rs.getString("product_id"),
                        rs.getString("notebook_brand"),
                        rs.getDouble("notebook_price"),
                        rs.getInt("notebook_quantity"),
                        rs.getInt("notebook_pageCount"));
            }
        }
    }
    private static void updateNotebookPrice(Connection conn, String id, double newPrice) throws SQLException {
        String sql = "UPDATE notebooks SET notebook_price = ? WHERE product_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, id);
            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " notebook(s).");
        }
    }

    private static void deleteNotebook(Connection conn, String id) throws SQLException {
        String sql = "DELETE FROM notebooks WHERE product_id = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Deleted notebook: " + id);
        }
    }
}