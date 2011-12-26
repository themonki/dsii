/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author leonardo
 */
public class User 
{
    private String username;
    private String password;
    private String creditCardNo;

    public User(String username, String password, String creditCardNo) {
        this.username = username;
        this.password = password;
        this.creditCardNo = creditCardNo;
    }

    public User() {}

    
    public String getCreditCardNo() {
        return creditCardNo;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setCreditCardNo(String creditCardNo) {
        this.creditCardNo = creditCardNo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
