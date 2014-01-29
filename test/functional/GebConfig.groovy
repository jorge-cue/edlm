/**
 * This is the Geb configuration file.
 * 
 * see: http://www.gebish.org/manual/current/configuration.html
 */

import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.chrome.ChromeDriver
import org.openqa.selenium.ie.InternetExplorerDriver

driver = { new FirefoxDriver() }

environments {
        
        // run as “grails -Dgeb.env=chrome test-app”
        // See: http://code.google.com/p/selenium/wiki/ChromeDriver
        chrome {
                driver = { new ChromeDriver() }
        }
        
        // run as “grails -Dgeb.env=firefox test-app”
        // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
        firefox {
                driver = { new FirefoxDriver() }
        }

        // run as “grails -Dgeb.env=ie test-app”
        // See: http://code.google.com/p/selenium/wiki/FirefoxDriver
        ie {
                driver = { new InternetExplorerDriver() }
        }
    
}

baseUrl = 'http://localhost:8080/edlm/'
