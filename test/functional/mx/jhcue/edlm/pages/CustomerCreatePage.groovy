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
class CustomerCreatePage extends Page {

    static url = 'customer/create'
    
    static at = { title == 'Create Customer' }
    
    static content = {
        homeButton(to: HomePage) { $('a.home', 0) }
        listButton(to: CustomerIndexPage) { $('a.list', 0) }
        form { module CustomerFormModule }
        submit(to: CustomerShowPage) { $('input[name=create]', 0) }
    }
}

