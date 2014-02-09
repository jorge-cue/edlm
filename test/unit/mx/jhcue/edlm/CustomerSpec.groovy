package mx.jhcue.edlm

import grails.test.mixin.TestFor
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Customer)
class CustomerSpec extends Specification {
    
    @Shared Date today
    @Shared Date yesterday
    @Shared Date tomorrow
    @Shared Date birthDate
    @Shared Map defaults
    
    def setupSpec() {
        birthDate = Date.parse('yyyy/mm/dd', '1958/8/18').clearTime()
        today = new Date().clearTime()
        yesterday = today - 1
        tomorrow = today + 1
        defaults = [
            firstName: 'NAME',
            fatherLastName: 'FATHER',
            motherLastName: 'MOTHER',
            curp: 'CURP010101HAGSAA01',
            rfc: 'CURP010101HO0',
            active: true,
            birthDate: birthDate
        ]
    }
    
    def "Customer properties are defined"() {
        given:
        def properties = defaults
        when:
        def customer = new Customer(properties)
        then:
        properties == customer.properties
    }
    
    def "Default properties have correct values"() {
        given: "A customer with default values"
        def customer = new Customer(defaults)
        when: "is validated..."
        customer.validate()
        then: "no error is reported"
        !customer.hasErrors()
    }
    
    @Unroll
    def "Customer #field with value '#value' is #description"() {
        given: "A set or default property values but #field set to #value"
        
        Map properties = [:]
        properties << defaults
        properties[field] = value
        
        when: "The customer is build and validated"
        
        Customer customer = new Customer(properties)
        boolean valid = customer.validate()
        
        // rejectedValue should be null if expectedCode is null,
        // otherwise the original value interpreting falses as null
        
        def expectedRejectedValue = expectedCode ? (value ?: null) : null
        
        then: "expected Code and rejectedValue should be null for valid or specified is expected an error"
        
        expectedCode == customer.errors.getFieldError(field)?.code
        expectedRejectedValue == customer.errors.getFieldError(field)?.rejectedValue
        
        and: "Other fields do not report error"
        
        ! customer.errors.getFieldErrors().grep { it.field != field }
        
        where: "Using next sample data"
        
        field            | value                   || expectedCode       | description
        'firstName'      | '-'.padLeft(129, '-')   || 'maxSize.exceeded' | 'invalid - first name maximum length is 128'
        'firstName'      | ''                      || 'nullable'         | 'invalid - first name cannot be blank'
        'firstName'      | null                    || 'nullable'         | 'invalid - first name is required'
        'fatherLastName' | '-'.padLeft(129, '-')   || 'maxSize.exceeded' | 'invalid - father last name maximum length is 128'
        'fatherLastName' | ''                      || 'nullable'         | 'invalid - father last name cannot be blank'
        'fatherLastName' | null                    || 'nullable'         | 'invalid - father last name is required'
        'motherLastName' | '-'.padLeft(129, '-')   || 'maxSize.exceeded' | 'invalid - mother last name maximum length is 128'
        'motherLastName' | ''                      || null               | 'valid - mother last name is optional'
        'motherLastName' | null                    || null               | 'valid - mother last name is optional'
        'curp'           | 'AAA9010101AAAAAAAA'    || 'matches.invalid'  | 'invalid - must meet CURP format AAAA999999AAAAXXXX'
        'curp'           | ''                      || 'nullable'         | 'invalid - curp cannot be blank'
        'curp'           | null                    || 'nullable'         | 'invalid - curp is required'
        'rfc'            | defaults.rfc.take(10)   || null               | 'valid - RFC without homonimia is valid, format AAAA999999'
        'rfc'            | 'AAA9010101XX0'         || 'matches.invalid'  | 'invalid - must meet RFC format AAAA999999XXX or AAAA999999'
        'active'         | false                   || null               | 'valid - active can be false'
        'active'         | null                    || 'nullable'         | 'invalid - active is required'
        'birthDate'      | yesterday               || null               | 'valid - birth date can be in the past'
        'birthDate'      | today                   || null               | 'valid - birth date can be today'
        'birthDate'      | tomorrow                || 'max.exceeded'     | 'invalid - birth date cannot be in the future'
    }
}
