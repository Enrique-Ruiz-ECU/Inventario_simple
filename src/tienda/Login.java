/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Enrique
 */
public class Login {
    
    public Boolean validarUsuario(String usuario, String password) throws SQLException{
        
        Boolean validar;
        
        Conexion connect = new Conexion();
       
            connect.abrirConexion();

        
       Statement statement = connect.conexion.createStatement();
       ResultSet resultado = statement.executeQuery("SELECT * FROM usuario WHERE user='"+usuario+"' AND password='"+password+"'");
        
        if (resultado.next()){ 

            validar = resultado.getString("user").equals(usuario) && resultado.getString("password").equals(password);
            
            
        }else{
            validar = false;
        }    
        
        connect.cerrarConexion();
        return validar;
    }
}
