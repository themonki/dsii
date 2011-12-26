
import javax.faces.application.FacesMessage;
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
public class LoginRequest 
{
    private String username;
    private String password;

    private UserHolder userHolder;
    
    private Dao dao;

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String login() 
    {
        dao = new Dao();
        
        FacesContext context = FacesContext.getCurrentInstance();
        userHolder = (UserHolder)context.getApplication().evaluateExpressionGet(context, "#{userHolder}", UserHolder.class);
        
        User user = dao.queryUser(username);
        
        if(user.getUsername() != null)
        {
            if (password.equals(user.getPassword()))
            {
                userHolder.setCurrentUser(user);
                return "catalog";
            } else 
            {
                context.addMessage(null, new FacesMessage(
                FacesMessage.SEVERITY_ERROR, "Login failed", null));
                return null;
            }
        }else
        {
            context.addMessage(null, new FacesMessage(
            FacesMessage.SEVERITY_ERROR, "Sorry, User NOT Exists!", null));
            return null;
        }   
    }
}
