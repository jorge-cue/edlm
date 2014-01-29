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
class CustomerEditPage extends Page {

    static url = 'customer/edit'
    
    static at = { title == 'Edit Customer' }
    
    static content = {
        version { $('input[name=version]', 0) }
        form { module CustomerFormModule }
        submit(to: CustomerShowPage) { $('.save', 0) }
    }
}
