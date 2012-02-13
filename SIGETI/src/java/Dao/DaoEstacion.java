/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.Estacion;
import Entidades.EstacionPrincipal;
import Entidades.EstacionParadero;
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

    public List<Estacion> findAllEstacion()
    {
        String sql_query =" SELECT id, ubicacion, estado FROM estacion";

        List<Estacion> estaciones = new ArrayList<Estacion>();

        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);

            while(table.next())
            {
                Estacion estacion = new Estacion();
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));

                estaciones.add(estacion);
            }

            fachada.cerrarConexion(conn);
        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaciones;

    }

    public List<EstacionParadero> findAllEstacionParadero()
    {
        String sql_query = " SELECT id, nombre, ubicacion from estacion_paradero JOIN"
                + " estacion ON estacion_paradero.id_estacion = estacion.id WHERE estado=true";

        List<EstacionParadero> estaciones = new ArrayList<EstacionParadero>();

        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);

            while(table.next())
            {
                EstacionParadero estacion = new EstacionParadero();
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));

                estaciones.add(estacion);
            }

            fachada.cerrarConexion(conn);

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaciones;

    }

     /**
     * Encuentra todas las estaciones principales existentes en la base de datos (activas)
     * @return  Lista que contiene objetos EstacionPrincipal, cada uno representa alguna encontrada
     * , si no existen estaciones el vector será vacio.
     * @author Leoanrdo Ríos
     */
    public List<EstacionPrincipal> findAllEstacionPrincipal() {
        String sqlConsulta = "SELECT id,nombre,ubicacion,estado,id_operario FROM estacion_principal JOIN "
                + " estacion ON estacion_principal.id_estacion = estacion.id WHERE estado=true";

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

    public Estacion findEstacion(Integer id)
    {
        String sql_query= "SELECT id, ubicacion, estado FROM estacion WHERE id="+ id;

        Estacion estacion= new Estacion();

        try{
            Connection conn= fachada.conectar();
            Statement sequence= conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);

            while(table.next())
            {
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));
            }
        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estacion;
    }

    public EstacionParadero findEstacionParadero(Integer id)
    {
        String sql_query="SELECT id, ubicacion, estado FROM estacion_paradero JOIN"
                + " estacion ON estacion_paradero.id_estacion= estacion.id";

        EstacionParadero estacion= new EstacionParadero();

        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);

            while(table.next())
            {
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));
            }

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estacion;
    }

    public EstacionPrincipal findEstacionPrincipal(Integer id)
    {
        String sql_query= " SELECT id, ubicacion, estado, nombre, id_operario FROM "
                + " estacion_principal JOIN estacion ON estacion_principal.id_estacion = "
                + " estacion.id";

        EstacionPrincipal estacion= new EstacionPrincipal();

        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);

            while(table.next())
            {
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));
                estacion.setNombre(table.getString("nombre"));
                estacion.setIdOperario(table.getString("id_operario"));
            }

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estacion;
    }
    
    public EstacionPrincipal findEstacionPrincipal(String nombre)
    {
        String sql_query= "SELECT id, ubicacion, estado, nombre, id_operario FROM "
                + " estacion_principal JOIN estacion ON estacion_principal.id_esatcion="
                + " estacion.id WHERE nombre= '" + nombre + "'";
        
        EstacionPrincipal estacion= new EstacionPrincipal();

        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);

            while(table.next())
            {
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));
                estacion.setNombre(table.getString("nombre"));
                estacion.setIdOperario(table.getString("id_operario"));
            }

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estacion;
    }

    public int saveEstacionParadero(EstacionParadero estacion)
    {
        int result=0;

        String sql_insert="INSERT INTO estacion (ubicacion, estado) VALUES('"
                + estacion.getUbicacion() +"' , '"+estacion.getEstado() +"')";

        try
        {
            Connection conn= fachada.conectar();
            Statement sequence= conn.createStatement();
            result= sequence.executeUpdate(sql_insert);

            int id=0;

            String sql_last_value="SELECT last_value FROM estacion_id_seq";

            ResultSet table= sequence.executeQuery(sql_last_value);

            while(table.next())
            {
                id=table.getInt("last_value");
            }

            String sql_insert_estacion="INSERT INTO estacion_paradero(id) VALUES('"
                    +id +"')";

            result=sequence.executeUpdate(sql_insert_estacion);

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

    public int saveEstacionPrincipal(EstacionPrincipal estacion)
    {
        int result=0;

        String sql_insert="INSERT INTO estacion (ubicacion, estado) VALUES('"
                + estacion.getUbicacion() +"' , '"+estacion.getEstado() +"')";

        try
        {
            Connection conn= fachada.conectar();
            Statement sequence= conn.createStatement();
            result= sequence.executeUpdate(sql_insert);

            int id=0;

            String sql_last_value="SELECT last_value FROM estacion_id_seq";

            ResultSet table= sequence.executeQuery(sql_last_value);

            while(table.next())
            {
                id=table.getInt("last_value");
            }

            String sql_insert_estacion="INSERT INTO estacion_principal(id, nombre, id_operario) VALUES('"
                    +id +"', '"+ estacion.getNombre() + "' , '" + estacion.getIdOperario() + "')";

            result=sequence.executeUpdate(sql_insert_estacion);

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
