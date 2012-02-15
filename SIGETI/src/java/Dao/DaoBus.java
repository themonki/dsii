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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edgar Andres Moncada
 */
public class DaoBus {

    FachadaBD fachada;

    public DaoBus() {
        fachada = new FachadaBD();
    }

    public int saveBus(Bus bus) {
        String sql_insert = "INSERT INTO bus (matricula,tipo,"
                + "capacidad,id_interno,estado,pertenece_ruta, estado_fisico";

        sql_insert += ") VALUES ('" + bus.getMatricula()
                + "','" + bus.getTipo()
                + "','" + bus.getCapacidad()
                + "','" + bus.getIdInterno()
                + "','" + bus.getEstado()
                + "','" + bus.getPerteneceRuta()
                + "','" + bus.getEstadoFisico()
                + "')";

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

    public Bus consultarBus(String id) {

        String sqlConsulta = "SELECT * from bus WHERE matricula = '" + id + "'"
                + " AND estado = 't'";
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
                bus.setEstadoFisico(table.getString("estado_fisico"));
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bus;
    }
    
    public List<Bus> consultarBuses(Bus b) {

        String sqlConsulta = "SELECT * from bus WHERE ";        
        sqlConsulta += " estado = 't'";
        boolean buscarPorMatricula = false;
        if(b.getMatricula()!=null && !b.getMatricula().equals("")){

            sqlConsulta += " AND ( matricula = '" + b.getMatricula()+"'";
            buscarPorMatricula=true;
        }
        
        if(b.getIdInterno()!=null && !b.getIdInterno().equals("")){
            if(buscarPorMatricula){
                sqlConsulta += " OR id_interno = '" + b.getIdInterno()+"'";
            }else{
                sqlConsulta += " AND (id_interno = '" + b.getIdInterno()+"'";
                buscarPorMatricula=true;
            }
        }
        
        if(b.getTipo()!=null && !b.getTipo().equals("0")){
            if(buscarPorMatricula){
                sqlConsulta += " OR tipo = '" + b.getTipo()+"'";
            }else{
                sqlConsulta += " AND tipo = '" + b.getTipo()+"'";
            }
        }   
        if(buscarPorMatricula){
            sqlConsulta += " ) ";
        }
        
                
        List<Bus> buses = null;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
            buses = new ArrayList<Bus>();
            int count =0;
            while (table.next()){
                count++;
                Bus bus = new Bus();
                bus.setCapacidad(table.getInt("capacidad"));
                bus.setEstado(table.getBoolean("estado"));
                bus.setIdInterno(table.getString("id_interno"));
                bus.setMatricula(table.getString("matricula"));
                bus.setPerteneceRuta(table.getString("pertenece_ruta"));
                bus.setTipo(table.getString("tipo"));
                bus.setEstadoFisico(table.getString("estado_fisico"));                
                buses.add(bus);
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buses;
    }

    public int updateBus(Bus bus) {
        String sql_update = "UPDATE bus SET ";

        sql_update += "tipo = '" + bus.getTipo()
                + "', capacidad = '" + bus.getCapacidad()
                + "', id_interno = '" + bus.getIdInterno()
                + "', estado = '" + bus.getEstado()
                + "', pertenece_ruta = '" + bus.getPerteneceRuta()
                + "', estado_fisico = '" + bus.getEstadoFisico()
                + "' WHERE matricula = '" + bus.getMatricula()
                + "';";
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_update);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result; //result
    }

    public int eraseBus(Bus bus) {
        String sql_erase = "DELETE FROM bus WHERE matricula = '" 
                + bus.getMatricula()+ "';";
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_erase);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result; //result
    }
    
    public int downBus(Bus bus) {
        String sql_down = "UPDATE bus SET estado = 'false' WHERE matricula = '"
                + bus.getMatricula()+ "';";
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_down);
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
        } catch (Exception e) {
            e.printStackTrace();

        }
        return result; //result
    }
    
    /**
     * Retorna true si la matricula esta en la base de datos
     */
    public boolean consultarMatriculaBus(String id) {

        String sqlConsulta = "SELECT matricula from bus WHERE matricula = '" 
                + id + "'";
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
            while (table.next()) {
                return true;
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Retorna true si la matricula esta en la base de datos
     */
    public boolean consultarIdInternoBus(String id) {

        String sqlConsulta = "SELECT id_interno from bus WHERE id_interno = '" 
                + id + "'";
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
            while (table.next()) {
                return true;
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public List<Bus> consultarBusesSinConductor() {

        String sqlConsulta = "SELECT * FROM bus b WHERE NOT EXISTS (SELECT matricula FROM conductor WHERE conduce_bus=matricula) and estado ='t'";
        
                
        List<Bus> buses = null;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);
            buses = new ArrayList<Bus>();
            int count =0;
            while (table.next()){
                count++;
                Bus bus = new Bus();
                bus.setCapacidad(table.getInt("capacidad"));
                bus.setEstado(table.getBoolean("estado"));
                bus.setIdInterno(table.getString("id_interno"));
                bus.setMatricula(table.getString("matricula"));
                bus.setPerteneceRuta(table.getString("pertenece_ruta"));
                bus.setTipo(table.getString("tipo"));
                bus.setEstadoFisico(table.getString("estado_fisico"));                
                buses.add(bus);
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buses;
    }
}
