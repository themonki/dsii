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
    
    
     public int saveUser(Usuario usuario) {

        int result=0;
        String sql_insert = "INSERT INTO usuario  (id,tipo_id,";
        String sql_values = "VALUES("+ usuario.getId()+",'"+usuario.getTipoId()+"',";
        
        if (usuario.getNombre()!=null &&!usuario.getNombre().equals("")){ sql_insert+= "nombre,";     sql_values+= usuario.getNombre()+",";   }
        if (usuario.getApellido()!=null &&!usuario.getApellido().equals("")){ sql_insert+= "apellido,";sql_values+=usuario.getApellido() +","; }
        if (usuario.getDireccion()!=null &&!usuario.getDireccion().equals("")){ sql_insert+= "direccion,"; sql_values+= usuario.getDireccion()+",";}
        if (usuario.getEmail()!=null &&!usuario.getEmail().equals("")){ sql_insert+= "email,"; sql_values+=usuario.getEmail() +",";}
        if (usuario.getFechaNacimiento()!=null &&!usuario.getFechaNacimiento().equals("")){ sql_insert+= "fecha_nacimiento,"; sql_values+="'"+usuario.getFechaNacimiento()+"',";}
        if (usuario.getTelefono()!=null &&!usuario.getTelefono().equals("")){ sql_insert+= "telefono,"; sql_values+=usuario.getTelefono()+",";}
      
       
        sql_insert+="adquiere_tarjeta,password,estado)   ";
        
        sql_values+=usuario.getAdquiereTarjeta()+","+usuario.getPassword()+","+usuario.getEstado()+")";
       
        sql_insert+=sql_values;
        
        System.err.println(sql_insert);

      
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
