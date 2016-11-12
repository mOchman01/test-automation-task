import org.openqa.selenium.chrome.ChromeDriver

System.setProperty('webdriver.chrome.driver', new File('target/chromedriver.exe').absolutePath)

driver = { new ChromeDriver() }

reportsDir = "target/geb-reports"