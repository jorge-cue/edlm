
import mx.jhcue.edlm.Authorities
import mx.jhcue.edlm.Customer
import mx.jhcue.edlm.User
import mx.jhcue.edlm.UserAuthorities

class BootStrap {

    def init = { servletContext ->
        environments {
            development {
                createAdminUser()
            }
            test {
                createAdminUser()
                createTestData()
            }
            production {
                createAdminUser()
            }
        }
    }
    def destroy = {
    }
    
    def createAdminUser() {
        def admin = new User(username: 'admin', password: 'Admin123').save(failOnError: true);
        def roleAdmin = new Authorities(authority: 'ROLE_ADMIN').save(failOnError: true);
        def roleUser = new Authorities(authority: 'ROLE_USER').save(failOnError: true);
        UserAuthorities.create(admin, roleAdmin, true);
        UserAuthorities.create(admin, roleUser, true);
    }

    def createTestData() {
        def customer = new Customer(
            firstName : 'Nombre',
            fatherLastName : 'Paterno',
            motherLastName : 'Materno',
            curp : 'CURP010101HDFXNR03',
            rfc : 'RFCA010101',
            active : 'True',
            birthDate : Date.parse('yyyy/mm/dd', '1958/8/18').clearTime()
        ).save(failOnError: true)
        assert customer.id == 1
    }
}
