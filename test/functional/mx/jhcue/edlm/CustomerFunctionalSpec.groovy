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

/**
 *
 * @author horacio
 */
class CustomerFunctionalSpec extends GebSpec {
        
    def language = System.getenv('lang') ?: 'en'

    def "Smoke Test"() {
        given:
        to HomePage, lang: language
        expect:
        at HomePage
    }
    
    def "Login"() {
        given:
        to LoginPage, lang: language
        expect:
        at LoginPage
        when:
        username = 'admin'
        password = 'Admin123'
        submit.click()
        then:
        at HomePage
    }

    def "Can create Customer"() {
        given:
        to LoginPage, lang: language
        expect:
        at LoginPage
        when:
        username = 'admin'
        password = 'Admin123'
        submit.click()
        to CustomerCreatePage
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
        submit.click()
        at CustomerShowPage
        then:
        firstName.text() == 'Nombre'
        fatherLastName.text() == 'Paterno'
        motherLastName.text() == 'Materno'
        curp.text() == 'CURP010101HDFXNR04'
        rfc.text() == 'RFCA030303XX0'
        active.text() == 'True'
        birthDate.text() == '1958-08-18 00:00:00 CST'
    }
    
    def "Can edit a just created Customer"() {
        given: "A created Customer"
        to LoginPage, lang: language
        expect:
        at LoginPage
        when:
        username = 'admin'
        password = 'Admin123'
        submit.click()
        to CustomerCreatePage
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
        submit.click()
        then:
        at CustomerShowPage
        when:
        edit.click()
        at CustomerEditPage
        then:
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
        submit.click()
        at CustomerShowPage
        then:
        firstName.text() == 'Name'
        fatherLastName.text() == 'Father'
        motherLastName.text() == 'Mother'
        curp.text() == 'FAMN650201HDFXNR03'
        rfc.text() == 'FAMN650201'
        birthDate.text() == '1965-02-01 00:00:00 CST'
    }

    def "Can edit an existing Customer"() {
        given:
        to LoginPage, lang: language
        expect:
        at LoginPage
        when:
        username = 'admin'
        password = 'Admin123'
        submit.click()
        to CustomerShowPage, 1
        at CustomerShowPage
        then:
        firstName.text() == 'Nombre'
        fatherLastName.text() == 'Paterno'
        motherLastName.text() == 'Materno'
        curp.text() == 'CURP010101HDFXNR03'
        rfc.text() == 'RFCA010101'
        active.text() == 'True'
        birthDate.text() == '1958-01-18 00:00:00 CST'
        when:
        edit.click()
        at CustomerEditPage
        then:
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
        submit.click()
        at CustomerShowPage
        then:
        firstName.text() == 'Name'
        fatherLastName.text() == 'Father'
        motherLastName.text() == 'Mother'
        curp.text() == 'FAMN650201HDFXNR08'
        rfc.text() == 'FAMN650208'
        birthDate.text() == '1965-02-01 00:00:00 CST'
    }
}
