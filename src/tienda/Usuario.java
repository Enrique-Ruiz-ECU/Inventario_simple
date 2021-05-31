package tienda;

import com.mysql.jdbc.Statement;
import java.sql.SQLException;



/**
 *
 * @author Enrique
 */
public class Usuario {
    
    private String user;
    private String password;
    private int permission;
    
    public Usuario(String user, String password, int permission){
        
        this.user = user;   
        this.password = password;
        this.permission = permission;
    }
    
    public void alta(){
        try {
            Conexion miConexion = new Conexion();
            
            
            miConexion.abrirConexion();
            Statement st = (Statement) miConexion.conexion.createStatement();
            st.executeUpdate("INSERT INTO usuario (user, password, permission)VALUES('"+user+"','"+password+"',"+permission+")"); 
            
            miConexion.cerrarConexion();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage()); 
        }

    }
    


    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPermission() {
        return permission;
    }

    public void setPermission(int permission) {
        this.permission = permission;
    }
    
    
}
