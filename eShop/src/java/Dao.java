
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leonardo
 */
public class Dao 
{
    private Fachada fachada;

    public Dao()
    {
        fachada = new Fachada();
    }
    
    public List<Product> getAllProducts()
    {
        String sql_query = "SELECT * from productos";
        List<Product> products = new ArrayList<Product>();
        
        try
      {
         Connection conn= fachada.conectar();
         //System.out.println("consultando en la bd");
         Statement sentence = conn.createStatement();
         ResultSet table = sentence.executeQuery(sql_query);
         
         while(table.next())
         {
             Product product = new Product();
             product.setId(table.getString("id"));
             //System.out.println("id " + product.getId());
             product.setNombre(table.getString("nombre"));
             //System.out.println("nombre " + product.getNombre());
             product.setPrecio(table.getInt("precio"));
             //System.out.println("precio " + product.getPrecio());
             
             products.add(product);
         }
        fachada.cerrarConexion(conn);
      }catch(SQLException e)
      {
         System.out.println(e); 
      }
      catch(Exception e)
      {
         System.out.println(e); 
      }
        return products;
    }
    
    public User queryUser(String username)
    {
        String sql_query = "SELECT * from usuario WHERE username='" + username +"'";
        
        User user = new User();
        try
        {
             Connection conn= fachada.conectar();
             //System.out.println("consultando en la bd");
             Statement sentence = conn.createStatement();
             ResultSet table = sentence.executeQuery(sql_query);
         
             while(table.next())
             {       
                 user.setUsername(table.getString("username"));
                 user.setPassword(table.getString("password"));
                 user.setCreditCardNo(table.getString("card_num"));
             }
            fachada.cerrarConexion(conn);
          }catch(SQLException e)
          {
             System.out.println(e); 
          }
          catch(Exception e)
          {
             System.out.println(e); 
          }
          return user;
    }
}
