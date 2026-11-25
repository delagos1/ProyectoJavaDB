/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.github.delagos1.proyectojavadb.proyectojavadb;

import java.sql.Connection;
import java.sql.SQLException;
/**
 *
 * @author david
 */
public class ProyectoJavaDB {

    public static void main(String[] args) {
        System.out.println("=== Probando conexion a la base de datos de SQL Server ===");
        Connection cn = ConexionDB.getConnection();
        
        try {
            if(cn != null && !cn.isClosed()) {
                System.out.println("Status: Online 🟢");
            }
            else {
                System.out.println("Status: Unable to connect to DB ❌");
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
