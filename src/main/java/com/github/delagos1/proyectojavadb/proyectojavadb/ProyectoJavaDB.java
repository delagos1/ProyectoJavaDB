/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.github.delagos1.proyectojavadb.proyectojavadb;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author david
 */
public class ProyectoJavaDB {

    public static void main(String[] args) {
        System.out.println("=== Probando conexion a la base de datos de SQL Server ===");
        Connection cn = ConexionDB.getConnection();
        
        if(cn != null) {
            System.out.println("Status: Online");
        }
        
        PreparedStatement psInsert = null;
        PreparedStatement psSelect = null;
        ResultSet rs = null;
        
        try {
            System.out.println("\n1 Insertando nuevo alumno");
            
            String insertSql = "INSERT INTO Alumnos(nombre, correo) VALUES (?, ?)";
            psInsert = cn.prepareStatement(insertSql); 
            
            psInsert.setString(1, "Carlos Martinez");
            psInsert.setString(2, "cmartinez@test.com");
            
            int rowsAffected = psInsert.executeUpdate();
            if(rowsAffected > 0) {
                System.out.println("Alumno ingresado exitosamente");
            }
            
            System.out.println("\n2 Consultando en la Tabla de Alumnos");
            
            String sqlSelect = "SELECT id, nombre, correo FROM Alumnos";
            
            psSelect = cn.prepareStatement(sqlSelect);
            rs = psSelect.executeQuery();
            
            System.out.println("\n===================================");
            System.out.println("ID  |      Nombre      |      Correo");
            System.out.println("====================================");
            
            while(rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                
                System.out.printf("%-3d | %-20s | %s\n", id, nombre, correo);
                System.out.println("===================================");
            }
        }
        catch(SQLException e) {
            System.err.println("Error en la verificacion. \n" + e.getMessage());
        }
        finally {
            ConexionDB.closeConnection();
        }
        
        
    }
}
