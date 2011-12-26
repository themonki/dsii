
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leonardo
 */

@ManagedBean
@SessionScoped
public class Cart implements Serializable
{
    private List<String> productsId;
    
    public Cart()
    {
        productsId = new ArrayList<String>();
    }
    
    public void add(String pid)
    {
        productsId.add(pid);
    }
    
    public List<String> getProductIds() 
    {
        return productsId;
    }

}
