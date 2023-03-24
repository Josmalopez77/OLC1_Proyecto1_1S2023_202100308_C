
package exregan;

import java.io.IOException;


/**
 *
 * @author Erwin14k
 */
public class LoginMethod {
    private static LoginMethod instance;
    String user="Jose";
    String password="12345";
    MainLogin mainLoginHandler = new MainLogin();
    MasterMindUI master_handler = new MasterMindUI();
    
    
    public static LoginMethod getInstance() {
        if(instance == null) {
            instance = new LoginMethod();            
        }
        return instance;
    }
    
    public boolean loginAuthentication(String user_parameter, String password_parameter) throws IOException {
        if(user.equals(user_parameter) && password.equals(password_parameter)) {
                    return true;
        }else{
            return false;
        } 
    }
    
}
