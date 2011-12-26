
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leonardo
 */
@ManagedBean
@ApplicationScoped
public class Catalog 
{
    private List<Product> products;
    private Dao dao;
        
    public Catalog()
    {
        dao = new Dao();
        products = dao.getAllProducts();
    }
    
    public List<Product> getProducts()
    {
        return products;
    }
    
    public Product getProduct(String pid) 
    {
        for (Product p : products) 
        {
            if (p.getId().equals(pid)) 
            {
                return p;
            }
        }
        System.out.println("no encontrado");
        return null;
    }
}
