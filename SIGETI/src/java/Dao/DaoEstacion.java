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
import java.util.logging.Level;
import java.util.logging.Logger;

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
        String sql_query = " SELECT id, ubicacion, estado from estacion_paradero JOIN"
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

    public List<EstacionParadero> findEstacionParadero(String ubicacion)
    {
        String ubicacion_sql="";
        if(!ubicacion.equals(""))
            ubicacion_sql= " WHERE ubicacion like '%"+ ubicacion + "%'";
        String sql_query="SELECT id, ubicacion, estado FROM estacion_paradero JOIN"
                + " estacion ON estacion_paradero.id_estacion= estacion.id"
                + ubicacion_sql;

        
        List<EstacionParadero> estaciones= new ArrayList<EstacionParadero>();
        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);

            while(table.next())
            {
                EstacionParadero estacion= new EstacionParadero();
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));
                estaciones.add(estacion);
            }

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaciones;
    }

    public List<EstacionParadero> findEstacionParaderoActivas(String ubicacion)
    {
        String ubicacion_sql="";
        if(!ubicacion.equals(""))
            ubicacion_sql= " and ubicacion like '%"+ ubicacion + "%'";
        String sql_query="SELECT id, ubicacion, estado FROM estacion_paradero JOIN"
                + " estacion ON estacion_paradero.id_estacion= estacion.id"
                + " WHERE estado=true " + ubicacion_sql;


        List<EstacionParadero> estaciones= new ArrayList<EstacionParadero>();
        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);

            while(table.next())
            {
                EstacionParadero estacion= new EstacionParadero();
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));
                estaciones.add(estacion);
            }

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaciones;
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
                + " estacion_principal JOIN estacion ON estacion_principal.id_estacion="
                + " estacion.id WHERE nombre = '" + nombre + "'";
        
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

    public List<EstacionPrincipal> findEstacionesPrincipales(String nombre, String ubicacion)
    {
        String sql_nombre="";
        String sql_ubicacion= "";
        String sql_where="";
        String sql_and="";
        if(!nombre.equals("") || !ubicacion.equals(""))
        {
            sql_where=" WHERE ";
        }
        if(!nombre.equals(""))
        {
            sql_nombre=" nombre like '%"+nombre+"%' ";
            if(!ubicacion.equals(""))
                sql_and=" and ";
            
        }
        if(!ubicacion.equals(""))
        {
            sql_ubicacion= " ubicacion like '%"+ubicacion+"%' ";
        }
        
        String sql_query= "SELECT id, ubicacion, estado, nombre, id_operario FROM "
                + " estacion_principal JOIN estacion ON estacion_principal.id_estacion="
                + " estacion.id " +sql_where + sql_nombre + sql_and + sql_ubicacion;

        List<EstacionPrincipal> estaciones= new ArrayList<EstacionPrincipal>();

        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            System.err.println(sql_query);
            ResultSet table = sequence.executeQuery(sql_query);


            while(table.next())
            {
                EstacionPrincipal estacion = new EstacionPrincipal();
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));
                estacion.setNombre(table.getString("nombre"));
                estacion.setIdOperario(table.getString("id_operario"));
                estaciones.add(estacion);
            }

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaciones;
    }

    public List<EstacionPrincipal> findEstacionesPrincipalesActivas(String nombre, String ubicacion)
    {
        String sql_nombre="";
        String sql_ubicacion= "";
        String sql_where="";
        if(!nombre.equals(""))
        {
            sql_nombre=" and nombre like '%"+nombre+"%' ";

        }
        if(!ubicacion.equals(""))
        {
            sql_ubicacion= " and ubicacion like '%"+ubicacion+"%' ";
        }
        String sql_query= "SELECT id, ubicacion, estado, nombre, id_operario FROM "
                + " estacion_principal JOIN estacion ON estacion_principal.id_estacion="
                + " estacion.id WHERE estado=true " + sql_nombre + sql_ubicacion;

        List<EstacionPrincipal> estaciones= new ArrayList<EstacionPrincipal>();

        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);


            while(table.next())
            {
                EstacionPrincipal estacion = new EstacionPrincipal();
                estacion.setId(table.getInt("id"));
                estacion.setUbicacion(table.getString("ubicacion"));
                estacion.setEstado(table.getBoolean("estado"));
                estacion.setNombre(table.getString("nombre"));
                estacion.setIdOperario(table.getString("id_operario"));
                estaciones.add(estacion);
            }

        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return estaciones;
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

            String sql_insert_estacion="INSERT INTO estacion_paradero(id_estacion) VALUES('"
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
        int insert=0;
        int id=0;

        try
        {
            Connection conn= fachada.conectar();
            Statement sequence= conn.createStatement();
            int executeUpdate = sequence.executeUpdate(sql_insert);

            String sql_last_value="SELECT last_value FROM estacion_id_seq";

            ResultSet table= sequence.executeQuery(sql_last_value);

            while(table.next())
            {
                id=table.getInt("last_value");
                insert++;
            }

            String sql_insert_estacion="INSERT INTO estacion_principal(id_estacion, nombre, id_operario) VALUES('"
                    +id +"', '"+ estacion.getNombre() + "' , '" + estacion.getIdOperario() + "')";

            result=sequence.executeUpdate(sql_insert_estacion);
            insert++;
        }catch (SQLException se) {
            if(insert==1)
            {
                try {
                    String sql = "ALTER SEQUENCE estacion_id_seq RESTART WITH " + id;
                    String delete = "DELETE FROM estacion WHERE id=" + id;
                    Connection conn = fachada.conectar();
                    Statement sequence = conn.createStatement();
                    int resultado = sequence.executeUpdate(sql);
                    resultado = sequence.executeUpdate(delete);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    public boolean existEstacion(String ubicacion)
    {
        String sql_query ="SELECT id FROM estacion WHERE ubicacion='"+ubicacion+"'";
        
        boolean exist=false;
        
        try{
            Connection conn= fachada.conectar();
            Statement sequence = conn.createStatement();
            ResultSet table = sequence.executeQuery(sql_query);
            
            while(table.next())
            {
                exist=true;
            }
        }catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return exist;
                
    }

    public int eliminarEstacion(Integer id)
    {
        String sql_update="UPDATE estacion SET estado=false WHERE id="+id;

        int result=0;
        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            result= sequence.executeUpdate(sql_update);

            fachada.cerrarConexion(conn);
        }catch(SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int editarEstacionParadero(EstacionParadero estacion)
    {
        String sql_update="UPDATE estacion SET ubicacion='"+estacion.getUbicacion()
                + "', estado='"+estacion.getEstado()+"' WHERE id="+estacion.getId();

        int result=0;

        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            result = sequence.executeUpdate(sql_update);

            fachada.cerrarConexion(conn);
        }catch(SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    public int editarEstacionPrincipal(EstacionPrincipal estacion)
    {
        String sql_update_estacion="UPDATE estacion SET ubicacion='"
                + estacion.getUbicacion() + "' , estado='"
                + estacion.getEstado() + "' WHERE id="+ estacion.getId();

        int result=0;
        try{
            Connection conn = fachada.conectar();
            Statement sequence = conn.createStatement();
            int executeSequence = sequence.executeUpdate(sql_update_estacion);

            if(executeSequence!=0)
            {
                String sql_update_estacion_principal="UPDATE estacion_principal SET "
                        + " nombre = '"+estacion.getNombre() + "' , id_operario='"
                        + estacion.getIdOperario() +"' WHERE id_estacion="
                        + estacion.getId();

                result=sequence.executeUpdate(sql_update_estacion_principal);
            }
        }catch(SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
