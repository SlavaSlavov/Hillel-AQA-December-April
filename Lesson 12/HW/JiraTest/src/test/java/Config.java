import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.util.concurrent.TimeUnit;

public class Config {

        protected static WebDriver driver;
        protected static WebDriverWait wait;

        public Config (){
        }

        @BeforeTest
        protected void setUp() {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\Slavik\\Downloads\\chromedriver_win32\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized", "--incognito");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(driver, 15, 500);
            driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
            driver.get("http://jira.hillel.it:8080");
        }

        @AfterTest
        protected void finish() {
           driver.close();
        }
    }

