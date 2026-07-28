package org.example.Config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static final String url = "jdbc:mysql://localhost:3306/IntegraHUP";
    private static final String usuario = "root";
    private static final String password = "Jushe2023-23";

    public static Connection conectar() {
        Connection conexion = null;
        try{
            conexion = DriverManager.getConnection(url,usuario,password);
            System.out.println("Conexion correcta a MySQL");
        }
        catch(SQLException err){
            System.err.println("Error al conectarse a MySQL " + err);
        }
        return conexion;
    }
}
