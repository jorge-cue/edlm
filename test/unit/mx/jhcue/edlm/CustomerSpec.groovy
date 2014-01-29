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
    
    def "Default properties with correct values"() {
        given:
        1958 == defaults.birthDate.getYear()
        8 == defaults.birthDate.getMonth()
        18 == defaults.birthDate.getDay()
    }
    
    def "Customer properties are defined"() {
        given:
        def properties = defaults
        when:
        def customer = new Customer(properties)
        then:
        properties == customer.properties
    }
    
    @Unroll
    def "Customer #field with value '#value' is #description"() {
        given:
        Map properties = [:]
        properties.putAll(defaults)
        properties[field] = value
        when:
        Customer customer = new Customer(properties)
        boolean valid = customer.validate()
        then:
        expectedCode == customer.errors.getFieldError(field)?.code
        (expectedValid ? null : (value ?: null)) == customer.errors.getFieldError(field)?.rejectedValue
        ! customer.errors.getFieldErrors().grep { it.field != field }
        where:
        field            | value                   || expectedValid | expectedCode       | description
        'firstName'      | defaults.firstName      || true          | null               | 'valid'
        'firstName'      | '-'.padLeft(129, '-')   || false         | 'maxSize.exceeded' | 'invalid with code maxSize.exceeded'
        'firstName'      | ''                      || false         | 'nullable'         | 'invalid with code nullable'
        'firstName'      | null                    || false         | 'nullable'         | 'invalid with code nullable'
        'fatherLastName' | defaults.fatherLastName || true          | null               | 'valid'
        'fatherLastName' | '-'.padLeft(129, '-')   || false         | 'maxSize.exceeded' | 'invalid with code maxSize.exceeded'
        'fatherLastName' | ''                      || false         | 'nullable'         | 'invalid with code nullable'
        'fatherLastName' | null                    || false         | 'nullable'         | 'invalid with code nullable'
        'motherLastName' | defaults.motherLastName || true          | null               | 'valid'
        'motherLastName' | '-'.padLeft(129, '-')   || false         | 'maxSize.exceeded' | 'invalid with code maxSize.exceeded'
        'motherLastName' | ''                      || true          | null               | 'valid'
        'motherLastName' | null                    || true          | null               | 'valid'
        'curp'           | defaults.curp           || true          | null               | 'valid'
        'curp'           | 'AAA9010101AAAAAAAA'    || false         | 'matches.invalid'  | 'invalid with error code matches.invalid'
        'curp'           | ''                      || false         | 'nullable'         | 'invalid with error code nullable'
        'curp'           | null                    || false         | 'nullable'         | 'invalid with error code nullable'
        'rfc'            | defaults.rfc            || true          | null               | 'valid'
        'rfc'            | defaults.rfc.take(10)   || true          | null               | 'valid'
        'rfc'            | 'AAA9010101XX0'         || false         | 'matches.invalid'  | 'invalid with error code matches.invalid'
        'active'         | true                    || true          | null               | 'valid'
        'active'         | false                   || true          | null               | 'valid'
        'active'         | null                    || false         | 'nullable'         | 'invalid with error code nullable'
        'birthDate'      | birthDate               || true          | null               | 'valid'
        'birthDate'      | yesterday               || true          | null               | 'valid'
        'birthDate'      | today                   || true          | null               | 'valid'
        'birthDate'      | tomorrow                || false         | 'max.exceeded'     | 'invalid with error code max.exceeded'
    }
}
