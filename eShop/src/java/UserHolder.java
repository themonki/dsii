
import java.io.Serializable;
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
public class UserHolder implements Serializable
{
    private User currentUser;
    
    public User getCurrentUser() 
    {
        return currentUser;
    }
    
    public void setCurrentUser(User currentUser) 
    {
        this.currentUser = currentUser;
    }
}
