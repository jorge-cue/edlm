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
class CustomerShowPage extends Page {
    
    // Expects id parameter
    static url = 'customer/show'
	
    static at = { title == 'Show Customer' }
    
    static content = {
        home(to: HomePage) { $('a.home', 0) }
        linst(to: CustomerIndexPage) { $('a.list', 0) }
        create(to: CustomerCreatePage) { $('a.create', 0) }
        edit(to: CustomerEditPage) { $('a.edit', 0 ) }
        delete(to: CustomerIndexPage) { $('.delete', 0) }
        
        firstName { $('span', 'aria-labelledby': 'firstName-label') }
        fatherLastName { $('span', 'aria-labelledby': 'fatherLastName-label') }
        motherLastName { $('span', 'aria-labelledby' : 'motherLastName-label') }
        curp { $('span', 'aria-labelledby': 'curp-label') }
        rfc { $('span', 'aria-labelledby': 'rfc-label') }
        active { $('span', 'aria-labelledby': 'active-label') }
        birthDate { $('span', 'aria-labelledby': 'birthDate-label') }
    }
}
