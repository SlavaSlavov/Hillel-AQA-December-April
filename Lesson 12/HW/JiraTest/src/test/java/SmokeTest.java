import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class SmokeTest {

    protected static WebDriver driver;
    protected static WebDriverWait wait;
    protected void sleep(int time) {
        try {
            Thread.sleep(time * 1000);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @BeforeTest
    protected void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Slavik\\Downloads\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized", "--incognito");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, 15, 500);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.get("http://jira.hillel.it:8080");
    }

    @DataProvider(name = "Authorization")
    public Object[][] dataForAuthorization(){
        return new Object[][]{
                {"autorob","forautotests"}
        };
    }

    @Test (dataProvider = "Authorization", priority = 1)
    private void logIn (String account, String pass){
        driver.findElement(By.cssSelector("#login-form-username")).sendKeys(account);
        driver.findElement(By.cssSelector("#login-form-password")).sendKeys(pass);
        driver.findElement(By.cssSelector("#login")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("[data-username=\""+account+"\"]")));
    }

    @Test (priority = 2)
    private void createTicket(){
        driver.findElement(By.cssSelector("#create_link")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#create-issue-dialog")));
        driver.findElement(By.cssSelector("#project-field")).sendKeys("Hillel QA 1/11/2018 (HQ)");
        driver.findElement(By.cssSelector("#summary")).click();
        sleep(2);
        driver.findElement(By.cssSelector("#summary")).sendKeys("AUTOTEST");
        driver.findElement(By.cssSelector("#create-issue-submit")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#aui-flag-container")));
        String testline =  driver.findElement(By.cssSelector(".issue-created-key.issue-link")).getAttribute("data-issue-key");
        Assert.assertEquals("Issue " + testline + " - AUTOTEST has been successfully created.", driver.findElement(By.cssSelector(".aui-message.aui-message-success")).getText());
        sleep(5);
    }

    @Test (priority = 3)
    private void openTicket(){
        driver.findElement(By.cssSelector("#find_link")).click();
        sleep(2);
        driver.findElement(By.cssSelector("div#issues_history_main > ul > li:nth-child(1) > a")).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#issue-content")));
    }

    @Test (priority = 4)
    private void changeTicketStatus(){
        driver.findElement(By.cssSelector("#opsbar-opsbar-transitions > li:nth-child(2)")).click();
        sleep(5);
        Assert.assertEquals("IN PROGRESS", driver.findElement(By.cssSelector("#status-val > span")).getText());
    }

    @AfterTest
    private void finish(){
        driver.close();
    }
}
