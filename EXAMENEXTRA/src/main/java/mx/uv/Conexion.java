package mx.uv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    //private static String url = "jdbc:mysql://db4freetest.net:3306/sisweb";
    //private static String url = "jdbc:mysql://127.0.0.1:3306/sisweb";
    private static String url = "jdbc:mysql://db4freetest.net:3306/sisweb";
    private static String driverName = "com.mysql.jdbc.Driver"; // com.mysql.cj.jdbc.Driver
    private static String username = "eduardompp";
    private static String password = "ed(!19gmp102g";
    // variable de conexion
    private static Connection connection = null;

    public static Connection getConnection(){
        try {
            Class.forName(driverName);
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println(" SQL:" + e);
        } catch (ClassNotFoundException e){
            System.out.println("Driver:" + e);
        }
        return connection;
    }
   
}