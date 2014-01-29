package mx.jhcue.edlm

import grails.transaction.Transactional

@Transactional
class CustomerService {

    def createCustomer() {
       new Customer(params)
    }
    
    def saveCustomer(Customer customer) {
        customer.save(failOnError: true)
    }
}
