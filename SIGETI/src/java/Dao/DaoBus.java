/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Entidades.Bus;
import Utilidades.FachadaBD;
import java.sql.Connection;
import java.sql.ResultSet;
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
        } catch (SQLException se) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; //result
    }
     
     public Bus consultarBus(String id){

         String sqlConsulta = "SELECT * from bus WHERE matricula = '" + id + "'";
         Bus bus = new Bus();

         try {
             Connection conn = fachada.conectar();
             Statement sentence = conn.createStatement();
             ResultSet table = sentence.executeQuery(sqlConsulta);

             while (table.next()) {
                 bus.setCapacidad(table.getInt("capacidad"));
                 bus.setEstado(table.getBoolean("estado"));
                 bus.setIdInterno(table.getString("id_interno"));
                 bus.setMatricula(table.getString("matricula"));
                 bus.setPerteneceRuta(table.getString("pertenece_ruta"));
                 bus.setTipo(table.getString("tipo"));
             }
             fachada.cerrarConexion(conn);
         } catch (SQLException se) {
             se.printStackTrace();
         } catch (Exception e) {
             e.printStackTrace();
         }
         return bus;
     }
     
     
     public int updateBus(Bus bus) {
        String sql_update = "UPDATE bus SET ";

        sql_update += "tipo = '" + bus.getTipo()
                + "', capacidad = '" + bus.getCapacidad()
                + "', id_interno = '" + bus.getIdInterno()
                + "', estado = '" + bus.getEstado()
                + "', pertenece_ruta'" + bus.getPerteneceRuta()
                + "' WHERE matricula = '" + bus.getMatricula()
                + "';";

        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_update);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; //result
    }
    
}
