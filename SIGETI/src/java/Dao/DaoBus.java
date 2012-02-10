/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Entidades.Bus;
import Utilidades.FachadaBD;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Edgar Andres Moncada
 */

public class DaoBus {
    FachadaBD fachada;
            
    public DaoBus(){
        fachada = new FachadaBD();
    }
    
     public int saveBus(Bus bus) {
        String sql_insert = "INSERT INTO bus (matricula,tipo,"
                + "capacidad,id_interno,estado,pertenece_ruta";
        
        sql_insert += ") VALUES ('" + bus.getMatricula()
                + "','" + bus.getTipo()
                + "','" + bus.getCapacidad()
                + "','" + bus.getIdInterno()
                + "','" + bus.getEstado()
                + "','" + bus.getPerteneceRuta()
                +"')";

        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
            JOptionPane.showMessageDialog(null, "Siii");
        } catch (SQLException se) {
                        JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; //result
    }
    
}
