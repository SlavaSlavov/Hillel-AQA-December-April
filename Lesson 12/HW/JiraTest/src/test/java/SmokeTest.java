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
    @DataProvider(name = "Ticket")
    public Object[][] dataForTicketFields(){
        return new Object[][]{
                {"Hillel Test Projects","AUTOTEST"}
        };
    }
    @DataProvider(name = "Ticket status")
    public Object[][] dataForTicketStatus(){
        return new  Object[][]{
                {"In Progress"}
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

    // Ticket page elements
    private By issueLinkInHeader = By.cssSelector("a#key-val");

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

    @Test (priority = 2, dataProvider = "Ticket", description = "Create ticket")
    private void createTicket(String projectName, String summaryText){
        driver.findElement(createIssueButton).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#create-issue-dialog")));
        clearAndFill(projectNameField, projectName);
        sleep(2);
        clearAndFill(summaryField,summaryText).submit();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#aui-flag-container")));
        Assert.assertEquals("Issue " + driver.findElement(By.cssSelector(".issue-created-key.issue-link")).getAttribute("data-issue-key") +
                " - " + summaryText + " has been successfully created.",
                driver.findElement(By.cssSelector(".aui-message.aui-message-success")).getText());
        sleep(5);
    }

    @Test (priority = 3, description = "Open ticket")
    private void openTicket(){
        driver.findElement(issuesButton).click();
        sleep(2);
        String ticketNumber = driver.findElement(firstRecentIssueOption).getAttribute("data-issue-key");
        driver.findElement(firstRecentIssueOption).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#issue-content")));
        Assert.assertEquals(ticketNumber, driver.findElement(issueLinkInHeader).getText());
    }

    @Test (priority = 4, dataProvider = "Ticket status", description = "Change status")
    private void changeTicketStatus(String status){
        driver.findElement(By.xpath("//a[contains(@class, 'issueaction-workflow-transition') and contains(.//span, '" + status + "')]")).click();
        sleep(5);
        Assert.assertEquals(status.toLowerCase(), driver.findElement(By.cssSelector("span#status-val > span")).getText().toLowerCase());
    }

    @AfterTest
    private void finish(){
        driver.close();
    }
}
