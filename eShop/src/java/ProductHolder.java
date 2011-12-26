
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
public class ProductHolder 
{
    private Product currentProduct;
    private Cart cart;
    private Catalog catalog;
    
    public String getProductId() 
    {
        String id;
        
        id =  currentProduct != null ? currentProduct.getId() : null;
        System.out.println("getProductId " + id);
        return id;
    }
    
    public void setProductId(String pid) 
    {
        FacesContext context = FacesContext.getCurrentInstance();
        catalog = (Catalog)context.getApplication().evaluateExpressionGet(context,"#{catalog}", Catalog.class);
        currentProduct = catalog.getProduct(pid);
    }

    public Product getCurrentProduct() 
    {
        return currentProduct;
    }

    public void setCurrentProduct(Product currentProduct) 
    {
        this.currentProduct = currentProduct;
    }
    
    public String addToCart()
    {
        FacesContext context = FacesContext.getCurrentInstance();
        cart = (Cart)context.getApplication().evaluateExpressionGet(context, "#{cart}", Cart.class);
        cart.add(currentProduct.getId());
        return "added";
    }
}
