/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion {
    
    private final String user = "root"; 
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3307/proyectojava";
    
    Connection conexion;
     
    
    public void abrirConexion() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage()); 
        }
            conexion = (Connection)DriverManager.getConnection(url, user, password);
    } 
    
    public void cerrarConexion() throws SQLException{
                if(conexion!=null) 
                    conexion.close();
                
                conexion=null;
    }   
}
