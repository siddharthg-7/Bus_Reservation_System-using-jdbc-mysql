import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * DatabaseConnection - Utility class for managing MySQL database connections
 * Handles connection establishment and database/table initialization
 */
public class DatabaseConnection {

    // Database connection parameters
    private static final String DB_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "bus_reservation";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "siddu";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    /**
     * Establishes and returns a connection to the bus_reservation database
     * Auto-creates database and tables if they don't exist
     * 
     * @return Connection object to the database
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Load MySQL JDBC driver
            Class.forName(DRIVER);

            // First, connect without specifying database to create it if needed
            Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            Statement stmt = conn.createStatement();

            // Create database if it doesn't exist
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + DB_NAME);
            stmt.close();
            conn.close();

            // Now connect to the specific database
            conn = DriverManager.getConnection(DB_URL + DB_NAME, DB_USER, DB_PASSWORD);

            // Create tables if they don't exist
            createTables(conn);

            return conn;

        } catch (ClassNotFoundException e) {
            throw new SQLException("MySQL JDBC Driver not found. Please add mysql-connector-j jar to classpath.", e);
        }
    }

    /**
     * Creates the required database tables if they don't exist
     * 
     * @param conn Active database connection
     * @throws SQLException if table creation fails
     */
    private static void createTables(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        // Create bus_info table
        String createBusInfoTable = "CREATE TABLE IF NOT EXISTS bus_info (" +
                "bus_number VARCHAR(20) PRIMARY KEY, " +
                "bus_name VARCHAR(100) NOT NULL, " +
                "source VARCHAR(100) NOT NULL, " +
                "destination VARCHAR(100) NOT NULL, " +
                "departure_time TIME NOT NULL, " +
                "arrival_time TIME NOT NULL, " +
                "fare DECIMAL(10, 2) NOT NULL, " +
                "total_seats INT NOT NULL" +
                ")";

        // Create reservations table
        String createReservationsTable = "CREATE TABLE IF NOT EXISTS reservations (" +
                "id INT AUTO_INCREMENT PRIMARY KEY, " +
                "passenger_name VARCHAR(100) NOT NULL, " +
                "bus_number VARCHAR(20) NOT NULL, " +
                "travel_date DATE NOT NULL, " +
                "seats INT NOT NULL, " +
                "FOREIGN KEY (bus_number) REFERENCES bus_info(bus_number)" +
                ")";

        stmt.executeUpdate(createBusInfoTable);
        stmt.executeUpdate(createReservationsTable);

        // Insert sample bus data if table is empty
        insertSampleData(conn);

        stmt.close();
    }

    /**
     * Inserts sample bus data for testing purposes
     * Only inserts if the bus_info table is empty
     * 
     * @param conn Active database connection
     * @throws SQLException if insertion fails
     */
    private static void insertSampleData(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        // Check if data already exists
        var rs = stmt.executeQuery("SELECT COUNT(*) FROM bus_info");
        rs.next();
        int count = rs.getInt(1);
        rs.close();

        // Only insert if table is empty
        if (count == 0) {
            String[] sampleBuses = {
                    "INSERT INTO bus_info VALUES ('BUS001', 'Express Deluxe', 'Mumbai', 'Pune', '06:00:00', '09:30:00', 350.00, 40)",
                    "INSERT INTO bus_info VALUES ('BUS002', 'Volvo AC', 'Delhi', 'Jaipur', '08:00:00', '13:00:00', 800.00, 45)",
                    "INSERT INTO bus_info VALUES ('BUS003', 'Sleeper Coach', 'Bangalore', 'Chennai', '22:00:00', '06:00:00', 950.00, 35)",
                    "INSERT INTO bus_info VALUES ('BUS004', 'Semi Sleeper', 'Hyderabad', 'Vijayawada', '14:00:00', '18:30:00', 450.00, 50)",
                    "INSERT INTO bus_info VALUES ('BUS005', 'Luxury AC', 'Kolkata', 'Bhubaneswar', '07:30:00', '15:00:00', 750.00, 38)"
            };

            for (String sql : sampleBuses) {
                stmt.executeUpdate(sql);
            }
        }

        stmt.close();
    }

    /**
     * Test method to verify database connection
     */
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            System.out.println("Database connection successful!");
            System.out.println("Database: " + DB_NAME);
            conn.close();
        } catch (SQLException e) {
            System.err.println("Database connection failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
