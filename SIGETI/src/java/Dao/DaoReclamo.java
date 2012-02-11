/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Entidades.Reclamo;
import Utilidades.FachadaBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Yerminson Gonzalez
 */

public class DaoReclamo {
    FachadaBD fachada;
            
    public DaoReclamo(){
        fachada = new FachadaBD();
    }
    
     public int saveReclamo(Reclamo reclamo) {
        String sql_insert = "INSERT INTO bus (matricula,tipo,"
                + "capacidad,id_interno,estado,pertenece_ruta";
        
        sql_insert += ") VALUES ('" + reclamo.getTicket()
                + "','" + reclamo.getFecha()
                + "','" + reclamo.getDescripcion()
                + "','" + reclamo.getMotivo()
                + "','" + reclamo.getEstado()               
                +"')";

        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
            JOptionPane.showMessageDialog(null, "Inserte Reclamo");
        } catch (SQLException se) {
                        JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; //result
    }
    
}