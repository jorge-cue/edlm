
package mx.jhcue.edlm.pages

import geb.Page

/**
 *
 * @author horacio
 */
class LoginPage extends Page {
    
    static url = 'login/auth'
    
    static at = { title == 'Login' }
    
    static contains = {
        username { $('#username', 0) }
        password { $('#password', 0) }
        rememberMe { $('#remember_me', 0) }
        submit(to: HomePage) { $('#submit', 0) }
    }
	
}

