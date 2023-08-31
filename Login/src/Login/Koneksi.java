package Login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Koneksi {
    private static Connection connection;
    public static Connection DBConnection(){
        
        String dbUrl = "jdbc:mysql://localhost:3306/appbelanja";
        String user = "root";
        String password = "";
        
        try{
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = (Connection) DriverManager.getConnection(dbUrl, user, password);
        }catch (SQLException exc){
            System.out.println("Koneksi error : " + exc.getMessage());
        }
        return connection;
    }
    
    public static void closeConnection(){
        try{
            connection.close();
        }catch(SQLException exc){
            System.out.println("FAILED TO CLOSE DATABASE CONNECTION : " + exc.getMessage());
        }
    }
}
