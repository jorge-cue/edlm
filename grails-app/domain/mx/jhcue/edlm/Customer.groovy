package mx.jhcue.edlm

import org.grails.databinding.BindingFormat

class Customer {
    String  firstName
    String  fatherLastName
    String  motherLastName
    String  curp
    String  rfc
    Boolean active
    Date    birthDate
    static constraints = {
        firstName       maxSize: 128
        fatherLastName  maxSize: 128
        motherLastName  maxSize: 128, nullable: true
        curp            maxSize: 18, matches: /^(?i:[a-z]{4}[0-9]{6}[a-z]{4}[a-z0-9]{4})$/, unique: true
        rfc             maxSize: 13, matches: /^(?i:[a-z]{4}[0-9]{6}([a-z0-9]{3})?)$/, unique: true
        birthDate       max: new Date()
    }
}
