/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.github.delagos1.proyectojavadb.proyectojavadb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author david
 */
public class ConexionDB {
    
    private static final String URL = "jdbc:sqlserver://localhost;databaseName=AvanzadaI;encrypt=true;trustCertificate=true;";
    private static final String USER = "sa";
    private static final String PASSWORD = "ProgramacionAI";
    
    private static Connection connection = null;
    
    private ConexionDB() {}
    
    public static Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()) {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("-> Conexion establecida.");
            } 
        }
        catch(ClassNotFoundException e) {
            System.err.println("Error! \nNo se encontro el driver. \n" + e.getMessage());
        }
        catch(SQLException e) {
            System.err.println("Error de SQL al conectar a la base de datos. \n" + e.getMessage());
        }
        return connection;
    }
    
    public static void closeConnection(){
        try {
            if(connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("-> Conexion con SQL Server cerrada.");
            }
        }
        catch(SQLException e) {
            System.err.println("Error al cerrar la conexion. \n" + e.getMessage());
        }
    }
}
