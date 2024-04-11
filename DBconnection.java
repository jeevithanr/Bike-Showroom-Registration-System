import java.sql.Connection;
import java.sql.DriverManager;

// Utility class for database connection
class DBconnection {
    // Method to establish a database connection
    public static Connection getConnection() {
        Connection connection = null;
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bike_showroom", "root", "Jeevithan9585");
        } catch (Exception e) {
            // Print stack trace in case of any exception
            e.printStackTrace();
        }
        return connection;
    }
}
