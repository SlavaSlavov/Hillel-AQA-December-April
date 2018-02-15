import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;


public class integersTest {


    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    protected void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Slavik\\Downloads\\chromedriver_win32\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        this.driver = new ChromeDriver(options);
        this.wait = new WebDriverWait(this.driver, 15, 500);
        this.driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        this.driver.get("https://www.random.org/integers/");
    }

    @Test
    protected void checkAllAreInt(){
        driver.findElement(By.cssSelector("[value=\"Get Numbers\"]")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".data")));
        String data = driver.findElement(By.cssSelector(".data")).getText();
        data.matches("(\\d{1,2}|100)");
    }
}
