package security;

import controllers.Secure;

public class Security extends Secure.Security {
    
    static boolean authenticate(String username, String password) {
        return username.equals("aleks") && password.equals("keukeukeu");
    }    
    
}