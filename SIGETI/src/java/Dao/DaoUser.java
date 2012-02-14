/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.Usuario;
import Utilidades.FachadaBD;

import java.sql.*;


/**
 *
 * @author Felipe
 */
public class DaoUser {
    
    
     private FachadaBD fachada;

    public DaoUser() {
        fachada = new FachadaBD();
    }
    
    
     public int saveCard(Usuario usuario) {

        int result=0;
        String sql_insert = "INSERT INTO usuario  (id,tipo_id,nombre,apellido,direccion,email,fecha_nacimiento,telefono,adquiere_tarjeta,password,estado)";
        
        sql_insert+="VALUES ("+ usuario.getId()+","+usuario.getTipoId()+","+usuario.getNombre()+","+usuario.getApellido()+","+usuario.getDireccion()+","+usuario.getEmail() +","+usuario.getFechaNacimiento()+","+usuario.getTelefono()+","+usuario.getAdquiereTarjeta()+","+usuario.getPassword()+","+usuario.getEstado();  ;

      
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
      
        
        return result;
        
    }
    
}
