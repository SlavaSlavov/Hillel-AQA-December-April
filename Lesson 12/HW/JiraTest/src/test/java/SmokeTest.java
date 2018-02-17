import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.security.Key;
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

    @DataProvider(name = "Authorization")
    public Object[][] dataForAuthorization(){
        return new Object[][]{
                {"autorob","forautotests"}
        };
    }
    @DataProvider(name = "Create ticket")
    public Object[][] dataForTicketFields(){
        return new Object[][]{
                {"Hillel Test Projects (HTP)","AUTOTEST"}
        };
    }
    // ELEMENTS
    // Login page elements
    private By loginField = By.cssSelector("input#login-form-username");
    private By passField = By.cssSelector("input#login-form-password");

    // Home page elements
    private By userOptions = By.cssSelector("a#header-details-user-fullname");
    private By createIssueButton = By.cssSelector("a#create_link");
    private By issuesButton = By.cssSelector("a#find_link");
    private By firstRecentIssueOption = By.cssSelector("div#issues_history_main > ul > li:nth-child(1) > a");

    // "Create issue" modal window elements
    private By projectNameField = By.cssSelector("input#project-field");
    private By summaryField = By.cssSelector("input#summary");

    //METHODS
    public static WebElement clearAndFill(By selector, String data) {
        WebElement element = driver.findElement(selector);
        element.clear();
        element.sendKeys(data, Keys.TAB);
        return element;
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

    @Test (priority = 1, dataProvider = "Authorization", description = "Authorization with valid credentials")
    private void logIn (String account, String pass){
        clearAndFill(loginField,account);
        clearAndFill(passField,pass).submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(userOptions));
        Assert.assertEquals(account, driver.findElement(userOptions).getAttribute("data-username"));
    }

    @Test (priority = 2, dataProvider = "Create ticket", description = "Create ticket")
    private void createTicket(String projectName, String summaryText){
        driver.findElement(createIssueButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#create-issue-dialog")));
        clearAndFill(projectNameField, projectName+", Keys.TAB");
        sleep(2);
        clearAndFill(summaryField,summaryText).submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#aui-flag-container")));
        Assert.assertEquals("Issue " + driver.findElement(By.cssSelector(".issue-created-key.issue-link")).getAttribute("data-issue-key") +
                " - " + summaryText + " has been successfully created.",
                driver.findElement(By.cssSelector(".aui-message.aui-message-success")).getText());
        sleep(5);
    }

    @Test (priority = 3)
    private void openTicket(){
        driver.findElement(issuesButton).click();
        sleep(2);
        driver.findElement(firstRecentIssueOption).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#issue-content")));
    }
//
//    @Test (priority = 4)
//    private void changeTicketStatus(){
//        driver.findElement(By.cssSelector("#opsbar-opsbar-transitions > li:nth-child(2)")).click();
//        sleep(5);
//        Assert.assertEquals("IN PROGRESS", driver.findElement(By.cssSelector("#status-val > span")).getText());
//    }

//    @AfterTest
//    private void finish(){
//        driver.close();
//    }
}
