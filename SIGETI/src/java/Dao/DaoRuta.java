package Dao;

import Entidades.Estacion;
import Entidades.Ruta;
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
public class DaoRuta {

    FachadaBD fachada;

    public DaoRuta() {
        fachada = new FachadaBD();
    }
    
    public List<Ruta> consultarAllRutas(){
        String sqlConsulta = "SELECT * from ruta WHERE estado = 't'";
        
        List<Ruta> nombreRutas = null;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
                ResultSet table = sentence.executeQuery(sqlConsulta);
            nombreRutas = new ArrayList<Ruta>();
            while (table.next()) {
                Ruta ruta = new Ruta();
                ruta.setDescripcion(table.getString("descripcion"));
                ruta.setEstado(table.getBoolean("estado"));
                ruta.setNombre(table.getString("nombre"));
                nombreRutas.add(ruta);                
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }       
       
       return nombreRutas;

    }

    public int saveRuta(Ruta ruta) {
         String sql_insert = "INSERT INTO ruta (nombre,descripcion,estado";
               
        sql_insert += ") VALUES ('"
                + ruta.getNombre()
                + "','" + ruta.getDescripcion()
                + "'," + ruta.getEstado()                
                + ");";

        System.err.println(sql_insert);
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
            System.out.println("Inserte Ruta");
        } catch (SQLException se) {
             se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String rutaValida(String nombre)       
    {
       String  nombreRuta = "";

        String sql_query = "SELECT *  FROM ruta WHERE nombre = '" + nombre + "';";

        try {

            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_query);
            
            while (table.next()) {

                nombreRuta = table.getString("nombre");                              

            }





        } catch (SQLException se) {
            // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        
        
        }


        return  nombreRuta;
    }
    
    
          
       public void insertarEstacionesRuta(List<Estacion> estaciones,String nombreRuta)
       {
       
           String sql_delete = "delete from ruta_formado_estacion where nombre='"+nombreRuta+"';";
           System.err.println(sql_delete);

           try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            sentence.executeUpdate(sql_delete);
            
            String sql_insert = "";
            for(int i = 0; i< estaciones.size();i++)
            {
                if(estaciones.get(i).getId() !=0)
                {
                sql_insert = "INSERT INTO ruta_formado_estacion VALUES('"+nombreRuta+"',"+estaciones.get(i).getId() +")";
                System.err.println(sql_insert);

                sentence.executeUpdate(sql_insert);                
                }
            
            }
            System.err.println("Se insertaron todas las estaciones para la ruta");


           
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       
       
       }
       
}

    
     
    
    
    