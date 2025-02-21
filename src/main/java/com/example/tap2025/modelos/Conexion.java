package com.example.tap2025.modelos;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    static private String DB = "restaurante";
    static private String USER = "admin";
    static private String PWD = "123";
    static private String HOST = "localhost";
    static private String PORT = "3306";
    //Esta es la variable goblal que va apuntar a la base de datos
    public static Connection connection;

    public static void createConnection() {
        //Todas las conexiones tienen que ser monitoreadas por try-catch
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Se trabaja con sockets, conectando un extremo al otro
            connection=DriverManager.getConnection("jdbc:mysql://"+HOST+":"+PORT+"/"+DB,USER,PWD);
            System.out.printf("Conexion establecida >:)");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
/*Socket: mecanismo de comunicacion*/