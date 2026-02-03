package csd214.bookstore.mysql;
import csd214.bookstore.pojos.DigitalMusic;
import java.sql.*;
import java.util.UUID;
public class JdbcDigitalMusicApp {
    private static final String URL = "jdbc:mysql://localhost:3307/bookstore";
    private static final String USER = "csd214";
    private static final String PASS = "itstudies12345";
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            // 1. Create Table
            createTable(conn);
            // 2. Insert
            System.out.println("--- INSERTING ---");
            DigitalMusic v1 = new DigitalMusic("In Utero","Nirvana","Rock",1993,100.68,"local-library.xwz/files/nirvana/uOu3ZJErDmo1gKvi");
            insertDigitalMusic(conn, v1);
            DigitalMusic v2 = new DigitalMusic("The Other Side of the Moon","Pink Floyd","Rock",1973,30.68,"local-library.xwz/files/pink-floyd/keNkPch2S3ThT8Lq");
            insertDigitalMusic(conn, v2);
            // 3. Read
            System.out.println("--- READING ---");
            listDigitalMusics(conn);
            // 4. Update
            System.out.println("--- UPDATING ---");
            updateDigitalMusicPrice(conn, "In Utero", 65.50);
            updateDigitalMusicPrice(conn, "The Other Side of the Moon", 45.50);

            // 5. Delete
            System.out.println("--- DELETING ---");
            deleteDigitalMusic(conn, "In Utero");
            deleteDigitalMusic(conn, "The Other Side of the Moon");
            listDigitalMusics(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void createTable(Connection conn) throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS digitalMusics (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "product_id VARCHAR(36), " +
                "digitalMusic_title VARCHAR(255), " +
                "digitalMusic_artist VARCHAR(255), " +
                "digitalMusic_genre VARCHAR(255), " +
                "digitalMusic_year INTEGER, " +
                "digitalMusic_price DOUBLE, " +
                "digitalMusic_link VARCHAR(255))";
        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Table 'digitalMusics' ready.");
        }
    }
    private static void insertDigitalMusic(Connection conn, DigitalMusic v) throws SQLException {
        // SECURITY: Use ? to prevent SQL Injection
        String sql = "INSERT INTO digitalMusics (product_id, digitalMusic_title, digitalMusic_artist, digitalMusic_genre, digitalMusic_year," +
                "digitalMusic_price, digitalMusic_link) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, v.getProductId()); // UUID
            ps.setString(2, v.getTitle());
            ps.setString(3, v.getArtist());
            ps.setString(4, v.getGenre());
            ps.setInt(5, v.getYear());
            ps.setDouble(6, v.getPrice());
            ps.setString(7, v.getLink());
            ps.executeUpdate();
            System.out.println("Saved: " + v.getTitle());
        }
    }
    private static void listDigitalMusics(Connection conn) throws SQLException {
        String sql = "SELECT * FROM digitalMusics";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.printf("ID: %d | UUID: %s | Title: %s | Artist: %s | Genre: %s | Year: %d | Price: $%.2f%n | Link: %s\n",
                        rs.getInt("id"),
                        rs.getString("product_id"),
                        rs.getString("digitalMusic_title"),
                        rs.getString("digitalMusic_artist"),
                        rs.getString("digitalMusic_genre"),
                        rs.getInt("digitalMusic_year"),
                        rs.getDouble("digitalMusic_price"),
                        rs.getString("digitalMusic_link"));
            }
        }
    }
    private static void updateDigitalMusicPrice(Connection conn, String title, double newPrice) throws SQLException {
        String sql = "UPDATE digitalMusics SET digitalMusic_price = ? WHERE digitalMusic_title = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newPrice);
            ps.setString(2, title);
            int rows = ps.executeUpdate();
            System.out.println("Updated " + rows + " digitalMusic(s).");
        }
    }

    private static void deleteDigitalMusic(Connection conn, String title) throws SQLException {
        String sql = "DELETE FROM digitalMusics WHERE digitalMusic_title = ?";
        try(PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, title);
            ps.executeUpdate();
            System.out.println("Deleted digitalMusic: " + title);
        }
    }
}