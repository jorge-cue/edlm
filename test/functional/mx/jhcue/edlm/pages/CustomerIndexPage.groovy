/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.jhcue.edlm.pages

import geb.Page

/**
 *
 * @author horacio
 */
class CustomerIndexPage extends Page {
	
    static url = 'customer'
    
    static at = { title == 'Customer List' }
    
    static content = {
        createButton(to: CustomerCreatePage) { $('a.create', 0) }
    }    
}

