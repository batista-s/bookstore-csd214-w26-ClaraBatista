package csd214.bookstore.mysql;
import csd214.bookstore.pojos.Vinyl;
import java.sql.*;
import java.util.UUID;
public class JdbcVinylApp {
    private static final String URL = "jdbc:mysql://localhost:3307/bookstore";
    private static final String USER = "csd214";
    private static final String PASS = "itstudies12345";
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // 1. Create Table
            createTable(conn);
            // 2. Insert
            System.out.println("--- INSERTING ---");
            Vinyl v1 = new Vinyl("AAA","HYUKOH","Indie Rock",2024,50.68,"Grey",15);
            insertVinyl(conn, v1);
            Vinyl v2 = new Vinyl("The Rise and Fall of a Midwest Princess","Chappell Roan","Pop",2023,80.68,"Yellow",80);
            insertVinyl(conn, v2);
            // 3. Read
            System.out.println("--- READING ---");
            listVinyls(conn);
            // 4. Update
            System.out.println("--- UPDATING ---");
            updateVinylPrice(conn, "AAA", 75.50);

            // 5. Delete
            System.out.println("--- DELETING ---");
            deleteVinyl(conn, "AAA");
            deleteVinyl(conn, "The Rise and Fall of a Midwest Princess");
            listVinyls(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS vinyls (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(36), " +
                "vinyl_title VARCHAR(255), " +
                "vinyl_artist VARCHAR(255), " +
                "vinyl_genre VARCHAR(255), " +
                "vinyl_year INTEGER, " +
                "vinyl_price DOUBLE, " +
                "vinyl_discColor VARCHAR(255), " +
                "vinyl_copies INTEGER)";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'vinyls' ready.");
        }
    }
    private static void insertVinyl(Connection conn, Vinyl v) throws SQLException {
        // SECURITY: Use ? to prevent SQL Injection
        String sql = "INSERT INTO vinyls (product_id, vinyl_title, vinyl_artist, vinyl_genre, vinyl_year," +
                "vinyl_price, vinyl_discColor, vinyl_copies) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getProductId()); // UUID
            ps.setString(2, v.getTitle());
            ps.setString(3, v.getArtist());
            ps.setString(4, v.getGenre());
            ps.setInt(5, v.getYear());
            ps.setDouble(6, v.getPrice());
            ps.setString(7, v.getDiscColor());
            ps.setInt(8, v.getCopies());
            ps.executeUpdate();
            System.out.println("Saved: " + v.getTitle());
        }
    }
    private static void listVinyls(Connection conn) throws SQLException {
        String sql = "SELECT * FROM vinyls";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | UUID: %s | Title: %s | Artist: %s | Genre: %s | Year: %d | Price: $%.2f%n | Disc Color: %s | Copies: %d \n",
                        rs.getInt("id"),
                        rs.getString("product_id"),
                        rs.getString("vinyl_title"),
                        rs.getString("vinyl_artist"),
                        rs.getString("vinyl_genre"),
                        rs.getInt("vinyl_year"),
                        rs.getDouble("vinyl_price"),
                        rs.getString("vinyl_discColor"),
                        rs.getInt("vinyl_copies"));
            }
        }
    }
    private static void updateVinylPrice(Connection conn, String title, double newPrice) throws SQLException {
        String sql = "UPDATE vinyls SET vinyl_price = ? WHERE vinyl_title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, title);
            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " vinyl(s).");
        }
    }

    private static void deleteVinyl(Connection conn, String title) throws SQLException {
        String sql = "DELETE FROM vinyls WHERE vinyl_title = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
            System.out.println("Deleted vinyl: " + title);
        }
    }
}