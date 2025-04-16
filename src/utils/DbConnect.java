package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnect {

    private static final String URL = "jdbc:postgresql://localhost:5432/avion"; 
    private static final String USER = "postgres"; 
    private static final String PASSWORD = "2005";  
    private static Connection connection = null;

    public static Connection getConnection() throws SQLException {
        Connection connection=null;
        if (connection == null) {
            try {
                
                Class.forName("org.postgresql.Driver");
                
                
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                // System.out.println("Connexion réussie à la base de données.");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new SQLException("Le driver PostgreSQL n'a pas été trouvé.", e);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new SQLException("Impossible de se connecter à la base de données.", e);
            }
        }
        return connection;
    }
    
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                // System.out.println("Connexion fermée.");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
