/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Dao;

import Entidades.Medida;
import Utilidades.FachadaBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author yerminson
 */
public class DaoMedida {
    
      FachadaBD fachada;

    public DaoMedida() {
        fachada = new FachadaBD();
    }

    public int saveMedida(Medida medida) {
        String sql_insert = "INSERT INTO medida (id,accion";
                

        sql_insert += ") VALUES (DEFAULT"
                + ",'" + medida.getAccion()          
                + "');";

        System.err.println(sql_insert);
        int result = 0;
        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            result = sentence.executeUpdate(sql_insert);
            fachada.cerrarConexion(conn);
            System.out.println("Inserte Medida");
        } catch (SQLException se) {
            // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result; //result
    }
     public List<Medida> findAllMedidas(int  ticket)
    {        
        String sqlConsulta = "SELECT * FROM medida NATURAL JOIN  (SELECT id_medida AS id FROM medida_reclamo_operario_agrega WHERE ticket ="+ticket+")as a;";
        System.err.println(sqlConsulta);

        List<Medida> medidas = new ArrayList<Medida>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                Medida medida = new Medida();

                medida.setId(Integer.parseInt(table.getString("id")));
                medida.setAccion(table.getString("accion"));
               
                medidas.add(medida);
                
                System.out.println(medida.getAccion());
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medidas;     

    }
    

       public List<Medida> findAllMedidas()
    {        
        String sqlConsulta = "SELECT * FROM medida;";
        System.err.println(sqlConsulta);

        List<Medida> medidas = new ArrayList<Medida>();

        try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sqlConsulta);

            while (table.next()) {
                Medida medida = new Medida();

                medida.setId(Integer.parseInt(table.getString("id")));
                medida.setAccion(table.getString("accion"));
               
                medidas.add(medida);
                
                System.out.println(medida.getAccion());
            }
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medidas;     

    }
       
       
      
       public void insertarMedidasReclamo(List<Medida> medidas,String idOperario,int ticket)
       {
       
           String sql_delete = "delete from medida_reclamo_operario_agrega where ticket="+ticket+";";
           System.err.println(sql_delete);

           try {
            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            sentence.executeUpdate(sql_delete);
            
            String sql_insert = "";
            for(int i = 0; i< medidas.size();i++)
            {
                if(medidas.get(i).getId() !=0)
                {
                sql_insert = "INSERT INTO medida_reclamo_operario_agrega VALUES("+idOperario+","+ticket+","+medidas.get(i).getId() +")";
                System.err.println(sql_insert);

                sentence.executeUpdate(sql_insert);                
                }
            
            }
            System.err.println("Se insertaron todas las medidas");


           
            fachada.cerrarConexion(conn);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
       
       
       
       }
       

    public int lastTicketId() {
        
        int idNumber = 0;

        String sql_count = "SELECT * FROM medida ORDER BY id DESC      LIMIT 1";

        try {

            Connection conn = fachada.conectar();
            Statement sentence = conn.createStatement();
            ResultSet table = sentence.executeQuery(sql_count);

            while (table.next()) {
                idNumber = Integer.parseInt(table.getString(1));

            }


        } catch (SQLException se) {
            // JOptionPane.showMessageDialog(null, se.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idNumber;
    }
    
}
