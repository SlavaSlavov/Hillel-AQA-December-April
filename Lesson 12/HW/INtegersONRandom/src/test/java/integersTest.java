import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


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
//        System.out.println(data);
        Assert.assertTrue(data.matches("(\\d{1,2}|100)\\s?"));
//        String[] arrayOfData = data.split("\\d{1,2}|100");
//        System.out.println(Arrays.toString(arrayOfData));
//        int[] n1 = new int[parts.length];
//        for(int n = 0; n < parts.length; n++) {
//            n1[n] = Integer.parseInt(parts[n]);
//        }


    }
}
