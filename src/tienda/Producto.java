/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienda;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane; 
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Enrique 
 */
public class Producto {
    private  final Conexion miConexion = new Conexion();
    private String nombre;
    private float precio; 
    private int stock, cantidad,codigo;
    

    public Producto(int codigo,String nombre, float precio, int stock, int cantidad) {
        this.codigo = codigo;      
        this.nombre = nombre; 
        this.precio = precio;
        this.stock  = stock; 
        this.cantidad = cantidad;   
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    public void alta() {
        
        try {
            miConexion.abrirConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Statement sentencia = null;
        try {
            sentencia = miConexion.conexion.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            sentencia.executeUpdate("INSERT INTO producto VALUES("+codigo+",'"+nombre+"',"+precio+","+stock+","+cantidad+")");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {   
            miConexion.cerrarConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        JOptionPane.showMessageDialog(null, "Producto insertado correctamente");
    }
    
        public void modificar(int codigoAntiguo) {
        
        try {
            miConexion.abrirConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        Statement sentencia = null;
        try {
            sentencia = miConexion.conexion.createStatement();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {
            sentencia.executeUpdate("UPDATE producto SET codigo="+codigo+",nombre='"+nombre+"',precio="+precio+",stock="+stock+" WHERE codigo="+codigoAntiguo+"");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        try {   
            miConexion.cerrarConexion();
            JOptionPane.showMessageDialog(null,"Produto modificado");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
    
    public String buscar(int codigo){
        
        try {
            String nombreProducto;   
            miConexion.abrirConexion();
            Statement statement =miConexion.conexion.createStatement();
            ResultSet rs = statement.executeQuery("SELECT nombre FROM producto where codigo ="+codigo+"");
            if(rs.next()){
                nombreProducto = rs.getString("nombre");
            }else{
                nombreProducto = null; 
            }
            return nombreProducto;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        } 
        
    }
    public DefaultTableModel consulta() {
           try {
            DefaultTableModel dtm = new DefaultTableModel();
            
            dtm.addColumn("Código");
            dtm.addColumn("Nombre");
            dtm.addColumn("Precio");
            dtm.addColumn("Stock"); 
            dtm.addColumn("Existencias");
            
            
            miConexion.abrirConexion();
            Statement statement =miConexion.conexion.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM producto"); 
            String[] producto = new String[5];
            while(resultSet.next()){
                
                    producto[0]=resultSet.getString("codigo");
                    producto[1]=resultSet.getString("nombre");
                    producto[2]=resultSet.getString("precio"); 
                    producto[3]=resultSet.getString("stock");
                    producto[4]=resultSet.getString("existencias");
                    
                    dtm.addRow(producto);
                
            }
            miConexion.cerrarConexion();
            return dtm; 
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void baja(int codigo) {
        try {
            miConexion.abrirConexion();
            Statement s = miConexion.conexion.createStatement();
            s.executeUpdate("DELETE FROM producto WHERE codigo ="+codigo+"");
            miConexion.cerrarConexion();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    
}