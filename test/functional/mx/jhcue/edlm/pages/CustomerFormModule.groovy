
package mx.jhcue.edlm.pages

import geb.Module

/**
 *
 * @author horacio
 */
class CustomerFormModule extends Module {

    static content = {
        firstName { $('#firstName', 0) }
        fatherLastName { $('#fatherLastName', 0) }
        motherLastName { $('#motherLastName', 0) }
        curp { $('#curp', 0) }
        rfc { $('#rfc', 0) }
        active { $('#active', 0) }
        birthDateDay { $('#birthDate_day', 0) }
        birthDateMonth { $('#birthDate_month', 0) }
        birthDateYear { $('#birthDate_year', 0) }
    }
    
}

