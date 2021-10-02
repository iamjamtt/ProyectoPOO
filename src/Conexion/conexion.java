package Conexion;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    Connection con;
    String url="jdbc:mysql://localhost:3306/store";
    String user="root";
    String pass="";
    
    public Connection conectar(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(url, user, pass);
        } catch (Exception e) {
            System.out.println("error " + e);
        }
        return con;
    }
}
