/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;
import Entidades.Reclamo;
import Utilidades.FachadaBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


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
        String sql_insert = "INSERT INTO reclamo (ticket,fecha,"
                + "descripcion,motivo,estado";
        
        sql_insert += ") VALUES (DEFAULT"
                + ",'" + reclamo.getFecha()
                + "','" + reclamo.getDescripcion()
                + "','" + reclamo.getMotivo()
                + "','" + reclamo.getEstado()               
                +"')";

        System.err.println(sql_insert);
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
            System.out.println( "Inserte Reclamo");
        } catch (SQLException se) {
                       // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; //result
    }
     
     
     public int lastTicketId()
     {
     
         int ticketNumber = 0;
         
         String sql_count = "SELECT * FROM reclamo ORDER BY ticket DESC LIMIT 1";
         
         try
         {
         
             Connection conn = fachada.conectar();
             Statement sentence = conn.createStatement();
             ResultSet table = sentence.executeQuery(sql_count);
             
             while(table.next())
             {
                 ticketNumber = Integer.parseInt(table.getString(1));
             
             }      
             
         
         } catch (SQLException se) {
                       // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
     
         return ticketNumber;
     
     }
     
     
    
}