package mx.jhcue.edlm



import spock.lang.*

/**
 *
 */
class CustomerIntegrationSpec extends Specification {

    final static defaults = [
        firstName: 'NAME',
        fatherLastName: 'FATHER',
        motherLastName: 'MOTHER',
        curp: 'CURP010101HAGSAA01',
        rfc: 'CURP010101HO0',
        active: true,
        birthDate: Date.parse('yy/mm/dd', '1958/08/18')
    ]
    
    def "Customer curp is unique in the database"() {
        given:
        new Customer(defaults).save(failOnError: true)
        when:
        def duplicate = new Customer(defaults)
        duplicate.rfc = 'FAMN020202'
        if (duplicate.validate()) {
            duplicate.save();
        }
        then:
        'unique' == duplicate.errors.getFieldError('curp')?.code
        defaults.curp == duplicate.errors.getFieldError('curp')?.rejectedValue
    }
    
    def "Customer rfc is unique in the database"() {
        given:
        new Customer(defaults).save(failOnError: true)
        when:
        def duplicate = new Customer(defaults)
        duplicate.curp = 'CURP020202MBCNBB02'
        if (duplicate.validate()) {
            duplicate.save();
        }
        then:
        'unique' == duplicate.errors.getFieldError('rfc')?.code
        defaults.rfc == duplicate.errors.getFieldError('rfc')?.rejectedValue
    }
}
