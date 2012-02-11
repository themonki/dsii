/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.EstacionPrincipal;
import Utilidades.FachadaBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de realizar transacciones en la base de datos relacionadas 
 * con estaciones.
 * @author
 */
public class DaoEstacion {

    private FachadaBD fachada;

    public DaoEstacion() {
        fachada = new FachadaBD();
    }

     /**
     * Encuentra todas las estaciones principales existentes en la base de datos
     * @return  Lista que contiene objetos EstacionPrincipal, cada uno representa alguna encontrada
     * , si no existen estaciones el vector será vacio.
     * @author Leoanrdo Ríos
     */
    public List<EstacionPrincipal> findAllEstacionPrincipal() {
        String sqlConsulta = "SELECT id,nombre,ubicacion,estado,id_operario FROM estacion_principal JOIN estacion ON estacion_principal.id_estacion = estacion.id";

        List<EstacionPrincipal> estaciones = new ArrayList<EstacionPrincipal>();
        
        try {
            
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                EstacionPrincipal estacion = new EstacionPrincipal();
                estacion.setEstado(table.getBoolean("estado"));
                estacion.setId(table.getInt("id"));
                estacion.setIdOperario(table.getString("id_operario"));
                estacion.setNombre(table.getString("nombre"));
                estacion.setUbicacion(table.getString("ubicacion"));
                
                estaciones.add(estacion);
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaciones;
    }
}
