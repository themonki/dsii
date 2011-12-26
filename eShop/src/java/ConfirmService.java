
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leonardo
 */
@ManagedBean
@RequestScoped
public class ConfirmService 
{
    private Cart cart;
    private Catalog catalog;
    private UserHolder uh;
    
    public ConfirmService()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        cart = (Cart)context.getApplication().evaluateExpressionGet(context, "#{cart}", Cart.class);
        catalog = (Catalog) context.getApplication().evaluateExpressionGet(context, "#{catalog}", Catalog.class);
    }
    public double getTotal() 
    {
        int total = 0;
        for (String pid : cart.getProductIds()) 
        {
            total += catalog.getProduct(pid).getPrecio();
        }
        return total;
    }
    
    public String getCreditCardNo() 
    {
        FacesContext context = FacesContext.getCurrentInstance();
        uh = (UserHolder)context.getApplication().evaluateExpressionGet(context, "#{userHolder}", UserHolder.class);
        if(uh.getCurrentUser() == null)
            System.out.println("usuario nullo");
        return uh.getCurrentUser().getCreditCardNo();
    }
}
