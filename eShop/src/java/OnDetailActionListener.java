
import javax.faces.application.Application;
import javax.faces.context.FacesContext;
import javax.faces.event.AbortProcessingException;
import javax.faces.event.ActionEvent;
import javax.faces.event.ActionListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leonardo
 */
public class OnDetailActionListener implements ActionListener
{
    public void processAction(ActionEvent e) throws AbortProcessingException
    {
        FacesContext context = FacesContext.getCurrentInstance();
        Application app = context.getApplication();
        Product p = (Product) app.evaluateExpressionGet(context, "#{p}", Product.class);
        System.out.println(p.getId());
        ProductHolder ph = (ProductHolder) app.evaluateExpressionGet(
            context,
            "#{productHolder}",
            ProductHolder.class);
        ph.setCurrentProduct(p);
    }
}
