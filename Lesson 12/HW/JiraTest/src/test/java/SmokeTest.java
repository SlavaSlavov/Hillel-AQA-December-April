import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;
import java.util.Arrays;
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
    String validPass = "forautotests";


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
    private By projectsButton = By.cssSelector("a#browse_link");
    private By createProjectOptionInProjects = By.cssSelector("a#project_template_create_link_lnk");
    private By administrationMenu = By.cssSelector("a#admin_menu");
    private By administrationMenuUser = By.cssSelector("a#admin_users_menu");

    // "Create issue" modal window elements
    private By projectNameField = By.cssSelector("input#project-field");
    private By summaryField = By.cssSelector("input#summary");

    // Ticket page elements
    private By issueLinkInHeader = By.cssSelector("a#key-val");

    // Confirm authorization page elements
    private By passFieldOnConfirmPage = By.cssSelector("input#login-form-authenticatePassword");

    // Project page elements
    private By projectNameOnProjectBoard = By.cssSelector("span#ghx-board-name");

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

    @Test (priority = 2)
    private void userList (){
        driver.findElement(administrationMenu).click();
        driver.findElement(administrationMenuUser).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form#login-form")));
        clearAndFill(passFieldOnConfirmPage, validPass).submit();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table#user_browser_table")));
        Assert.assertEquals("users", driver.findElement(By.cssSelector("div.aui-page-header-main > h2")).getText().toLowerCase());
    }

//    @Test (priority = 3)
//    private void createNewProject () {
//        driver.findElement(projectsButton).click();
//        driver.findElement(createProjectOptionInProjects).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#add-project-dialog")));
//        driver.findElement(By.cssSelector("button.create-project-dialog-create-button")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("ul.project-template-issuetype-list")));
//        driver.findElement(By.cssSelector("button.template-info-dialog-create-button")).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form#add-project-form")));
//        driver.findElement(By.cssSelector("input#name")).sendKeys("NewAutoTestProject");
//        sleep(2);
//
//        String projectKey = driver.findElement(By.cssSelector("input#key")).getAttribute("value");
//
//        driver.findElement(By.cssSelector("input#name")).submit();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#ghx-content-main")));
//        Assert.assertEquals(projectKey + " board",driver.findElement(projectNameOnProjectBoard).getText());
//
//    }

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
//
//    @Test (priority = 3, description = "Open ticket")
//    private void openTicket(){
//        driver.findElement(issuesButton).click();
//        sleep(2);
//        String ticketNumber = driver.findElement(firstRecentIssueOption).getAttribute("data-issue-key");
//        driver.findElement(firstRecentIssueOption).click();
//        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div#issue-content")));
//        Assert.assertEquals(ticketNumber, driver.findElement(issueLinkInHeader).getText());
//    }
//
//    @Test (priority = 4, dataProvider = "Ticket status", description = "Change status")
//    private void changeTicketStatus(String status){
//        driver.findElement(By.xpath("//a[contains(@class, 'issueaction-workflow-transition') and contains(.//span, '" + status + "')]")).click();
//        sleep(5);
//        Assert.assertEquals(status.toLowerCase(), driver.findElement(By.cssSelector("span#status-val > span")).getText().toLowerCase());
//    }
//
//    @AfterMethod
//    void afterM(ITestResult testResult){
//        if (!testResult.isSuccess()){
//            System.out.println(testResult.getMethod().getDescription() + " - Failed with data: " + Arrays.toString(testResult.getParameters()));
//        } else {
//            System.out.println(testResult.getMethod().getDescription() + " - Passed with data: " + Arrays.toString(testResult.getParameters()));
//        }
//    }
//
//    @AfterTest
//    private void finish(){
//        driver.close();
//    }
}
