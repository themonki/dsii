
import javax.faces.application.Application;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leonardo
 */
public class ForceLoginPhaseListener implements PhaseListener
{
    @Override
    public void afterPhase(PhaseEvent pe) {
        
    }

    @Override
    public void beforePhase(PhaseEvent pe) 
    {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        if (viewId.equals("/confirm.xhtml")) 
        {
            Application app = context.getApplication();
            UserHolder uh = (UserHolder) app.evaluateExpressionGet(context,
            "#{userHolder}", UserHolder.class);

            if (uh.getCurrentUser() == null) 
            {
                ViewHandler viewHandler = app.getViewHandler();
                UIViewRoot viewRoot = viewHandler.createView(context,
                "/login.xhtml");
                context.setViewRoot(viewRoot);
            }
        }
    }

    @Override
    public PhaseId getPhaseId() {
        return PhaseId.RENDER_RESPONSE;
    }
    
}
