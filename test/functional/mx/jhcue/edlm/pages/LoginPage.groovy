
package mx.jhcue.edlm.pages

import geb.Page

/**
 *
 * @author horacio
 */
class LoginPage extends Page {
    
    static url = 'login/auth'
    
    static at = { $('#loginForm', 0) != null }
    
    static content = {
        username { $('#username', 0) }
        password { $('#password', 0) }
        rememberMe { $('#remember_me', 0) }
        submit { $('#submit', 0) }
    }
}

