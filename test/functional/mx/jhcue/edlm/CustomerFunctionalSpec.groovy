/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package mx.jhcue.edlm

import geb.Browser
import geb.Page
import geb.PageChangeListener
import geb.spock.GebSpec

import mx.jhcue.edlm.pages.*
import org.springframework.test.annotation.Rollback

/**
 *
 * @author horacio
 */
class CustomerFunctionalSpec extends GebSpec {
        
    def language = System.getenv('lang') ?: 'en'

    def "Smoke Test"() {
        given:
        reportGroup "Smoke Test"
        to HomePage, lang: language
        report "Home Page"
        expect:
        at HomePage
    }
    
    def "Login"() {
        given:
        reportGroup "Login"
        to LoginPage, lang: language
        report "001 Login Page"
        expect:
        at LoginPage
        when:
        username = 'admin'
        password = 'Admin123'
        rememberMe = false
        report "002 Login Page"
        submit.click()
        report "003 Home Page"
        to HomePage
        then:
        at HomePage
    }

    @Rollback
    def "Can create a Customer"() {
        given:
        reportGroup "Can create a customer"
        to LoginPage, lang: language
        expect:
        at LoginPage
        when:
        username = 'admin'
        password = 'Admin123'
        rememberMe = false
        submit.click()
        to CustomerCreatePage
        report "001 Create Page"
        then:
        at CustomerCreatePage
        when:
        form.firstName = 'Nombre'
        form.fatherLastName = 'Paterno'
        form.motherLastName = 'Materno'
        form.curp = 'CURP010101HDFXNR04'
        form.rfc = 'RFCA030303XX0'
        form.active = true
        form.birthDateDay = '18'
        form.birthDateMonth = '8'
        form.birthDateYear = '1958'
        report "002 Create Customer FilledIn"
        submit.click()
        report "003 Create Customer Created"
        then:
        at CustomerShowPage
        and:
        firstName.text() == 'Nombre'
        fatherLastName.text() == 'Paterno'
        motherLastName.text() == 'Materno'
        curp.text() == 'CURP010101HDFXNR04'
        rfc.text() == 'RFCA030303XX0'
        active.text() == 'True'
        birthDate.text() == '1958-08-18 00:00:00 CST'
    }
    
    @Rollback
    def "Can edit a just created Customer"() {
        given: "A created Customer"
        reportGroup "Can edit a just created Customer"
        to LoginPage, lang: language
        expect:
        at LoginPage
        when:
        username = 'admin'
        password = 'Admin123'
        rememberMe = false
        submit.click()
        to CustomerCreatePage
        report "001 Customer Create"
        at CustomerCreatePage
        form.firstName = 'Nombre'
        form.fatherLastName = 'Paterno'
        form.motherLastName = 'Materno'
        form.curp = 'CURP580818HDFXNR03'
        form.rfc = 'RFCA580818'
        form.active = true
        form.birthDateDay = '18'
        form.birthDateMonth = '8'
        form.birthDateYear = '1958'
        report "002 Customer Create before submit"
        submit.click()
        report "003 Customer Show after submit"
        then:
        at CustomerShowPage
        when:
        edit.click()
        report "004 - Edit Customer"
        then:
        at CustomerEditPage
        and:
        form.firstName == 'Nombre'
        form.fatherLastName == 'Paterno'
        form.motherLastName == 'Materno'
        form.curp == 'CURP580818HDFXNR03'
        form.rfc == 'RFCA580818'
        form.active
        form.birthDateDay == '18'
        form.birthDateMonth == '8'
        form.birthDateYear == '1958'
        when:
        form.firstName = 'Name'
        form.fatherLastName = 'Father'
        form.motherLastName = 'Mother'
        form.curp = 'FAMN650201HDFXNR03'
        form.rfc = 'FAMN650201'
        form.active = false
        form.birthDateDay = '1'
        form.birthDateMonth = '2'
        form.birthDateYear = '1965'
        report "005 Edit new Customer 3"
        submit.click()
        report "006 Edit new Customer 4"
        then:
        at CustomerShowPage
        and:
        firstName.text() == 'Name'
        fatherLastName.text() == 'Father'
        motherLastName.text() == 'Mother'
        curp.text() == 'FAMN650201HDFXNR03'
        rfc.text() == 'FAMN650201'
        birthDate.text() == '1965-02-01 00:00:00 CST'
    }

    @Rollback
    def "Can edit an existing Customer"() {
        given:
        reportGroup "Can edit an existing Customer"
        to LoginPage, lang: language
        expect:
        at LoginPage
        when:
        username = 'admin'
        password = 'Admin123'
        rememberMe = false
        submit.click()
        to CustomerShowPage, 1
        report "001 Customer 1 Show"
        then:
        at CustomerShowPage
        and:
        firstName.text() == 'Nombre'
        fatherLastName.text() == 'Paterno'
        motherLastName.text() == 'Materno'
        curp.text() == 'CURP010101HDFXNR03'
        rfc.text() == 'RFCA010101'
        active.text() == 'True'
        birthDate.text() == '1958-01-18 00:00:00 CST'
        when:
        edit.click()
        report "002 Customer Edit"
        then:
        at CustomerEditPage
        and:
        form.firstName == 'Nombre'
        form.fatherLastName == 'Paterno'
        form.motherLastName == 'Materno'
        when:
        form.firstName = 'Name'
        form.fatherLastName = 'Father'
        form.motherLastName = 'Mother'
        form.curp = 'FAMN650201HDFXNR08'
        form.rfc = 'FAMN650208'
        form.active = false
        form.birthDateDay = '1'
        form.birthDateMonth = '2'
        form.birthDateYear = '1965'
        report "003 Customer Edit"
        submit.click()
        report "004 Customer Edit"
        then:
        at CustomerShowPage
        and:
        firstName.text() == 'Name'
        fatherLastName.text() == 'Father'
        motherLastName.text() == 'Mother'
        curp.text() == 'FAMN650201HDFXNR08'
        rfc.text() == 'FAMN650208'
        birthDate.text() == '1965-02-01 00:00:00 CST'
    }
    
    @Rollback
    def "Can delete an existing Customer"() {
        given:
        reportGroup "Can delete an existing Customer"
        to LoginPage, lang: language
        expect:
        at LoginPage
        when:
        username = 'admin'
        password = 'Admin123'
        rememberMe = false
        submit.click()
        via CustomerIndexPage
        report "001 Customer Index"
        via CustomerShowPage, 1
        report "002 Customer Show"
        then:
        at CustomerShowPage
        when:
        withConfirm(ok: true, wait: true) { delete.click() }
        report "003 Customer Deleted"
        then:
        at CustomerIndexPage
    }
}
