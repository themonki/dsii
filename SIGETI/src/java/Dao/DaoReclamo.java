/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.Medida;
import Entidades.Reclamo;
import Utilidades.FachadaBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Yerminson Gonzalez
 */
public class DaoReclamo {

    FachadaBD fachada;

    public DaoReclamo() {
        fachada = new FachadaBD();
    }

    public int saveReclamo(Reclamo reclamo) {
        String sql_insert = "INSERT INTO reclamo (ticket,fecha,"
                + "descripcion,motivo,estado,auxiliar_recibe,usuario_realiza";

        sql_insert += ") VALUES (DEFAULT"
                + ",'" + reclamo.getFecha()
                + "','" + reclamo.getDescripcion()
                + "','" + reclamo.getMotivo()
                + "','" + reclamo.getEstado()
                 + "','" + reclamo.getAuxiliarRecibe()
                 + "','" + reclamo.getUsuarioRealiza()
                + "');";

        System.err.println(sql_insert);
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
            System.out.println("Inserte Reclamo");
        } catch (SQLException se) {
            // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; //result
    }

    public int lastTicketId() {

        int ticketNumber = 0;

        String sql_count = "SELECT * FROM reclamo ORDER BY ticket DESC      LIMIT 1";

        try {

            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_count);

            while (table.next()) {
                ticketNumber = Integer.parseInt(table.getString(1));

            }


        } catch (SQLException se) {
            // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ticketNumber;

    }

    public Reclamo findReclamo(int ticket) {


        Reclamo reclamo = new Reclamo();

        String sql_query = "SELECT * FROM reclamo WHERE ticket = " + ticket + ";";

        try {

            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_query);

            while (table.next()) {


                reclamo.setTicket(Integer.parseInt(table.getString("ticket")));
                reclamo.setFecha(table.getString("fecha"));
                reclamo.setDescripcion(table.getString("descripcion"));
                reclamo.setMotivo(table.getString("motivo"));
                reclamo.setEstado(table.getString("estado"));
                reclamo.setAuxiliarRecibe(table.getString("auxiliar_recibe"));
                reclamo.setUsuarioRealiza(table.getString("usuario_realiza"));
            }





        } catch (SQLException se) {
            // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


        return reclamo;



    }

    public List<Reclamo> findAllReclamos() {
        String sqlConsulta = "SELECT * FROM reclamo;";

        List<Reclamo> reclamos = new ArrayList<Reclamo>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                Reclamo reclamo = new Reclamo();

                reclamo.setTicket(Integer.parseInt(table.getString("ticket")));
                reclamo.setFecha(table.getString("fecha"));
                reclamo.setDescripcion(table.getString("descripcion"));
                reclamo.setMotivo(table.getString("motivo"));
                reclamo.setEstado(table.getString("estado"));

                reclamos.add(reclamo);
                
                System.out.println(reclamo.getTicket());
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reclamos;
    }
    
    public String usuarioValido(String id)
    {
       String  nombreUsuario = "";

        String sql_query = "SELECT nombre FROM usuario WHERE id = '" + id + "';";

        try {

            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_query);
            
            while (table.next()) {

                nombreUsuario = table.getString("nombre");                              

            }





        } catch (SQLException se) {
            // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }


   
        return nombreUsuario;
    
    }

    public List<Reclamo> findReclamoPorEstado(String estado) {

        String sqlConsulta = "SELECT * FROM reclamo where estado ='"+estado+"';";
        if(estado == "")
        {
            
            sqlConsulta = "SELECT * FROM reclamo;";
        
        }
        
        System.err.println(sqlConsulta);

        List<Reclamo> reclamos = new ArrayList<Reclamo>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                Reclamo reclamo = new Reclamo();

                reclamo.setTicket(Integer.parseInt(table.getString("ticket")));
                reclamo.setFecha(table.getString("fecha"));
                reclamo.setDescripcion(table.getString("descripcion"));
                reclamo.setMotivo(table.getString("motivo"));
                reclamo.setEstado(table.getString("estado"));
                reclamo.setAuxiliarRecibe(table.getString("auxiliar_recibe"));
                reclamo.setUsuarioRealiza(table.getString("usuario_realiza"));

                reclamos.add(reclamo);
                
                System.out.println(reclamo.getTicket());
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return reclamos;     

    }

    public int updateReclamo(Reclamo reclamo) {
        
        
        System.out.println("Antes de actualizar  Reclamo");
         String sql_update = "UPDATE reclamo SET estado = '"+reclamo.getEstado()+"' WHERE ticket ="+reclamo.getTicket()+";";
               

        System.err.println(sql_update);
        int result = -1;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_update);
            fachada.cerrarConexion(conn);
            System.out.println("Actualice Reclamo");
        } catch (SQLException se) {
            // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; //result
    }
    
   
}