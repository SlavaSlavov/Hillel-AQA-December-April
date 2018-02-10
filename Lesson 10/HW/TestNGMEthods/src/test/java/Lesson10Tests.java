import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Lesson10Tests {

    TestsMethods testsMethods;

    @DataProvider(name = "lowerThat100")
    public Object [][] dataFor100Test(){
        return new Object[][]{
                {"99.99",Boolean.TRUE},
                {"100",Boolean.TRUE},
                {"101.01",Boolean.FALSE},
                {"",Boolean.FALSE},
                {"az",Boolean.FALSE},
                {"/*",Boolean.FALSE},
        };
    }
    @DataProvider(name = "greaterThat1450")
    public Object [][] dataFor1450Test(){
        return new Object[][]{
                {"1449.99",Boolean.TRUE},
                {"1450",Boolean.TRUE},
                {"1450.01",Boolean.FALSE},
                {"",Boolean.FALSE},
                {"az",Boolean.FALSE},
                {"/*",Boolean.FALSE},
        };
    }
    @DataProvider(name = "domainInEmail")
    public Object [][] dataForDomainInEmailsTest(){
        return new Object[][]{
                {"abc@gmail.com,asd@yandex.ru,abc@gmail.com",Boolean.TRUE},
                {"abc@yandex.ru,asd@yandex.ru,abc@yandex.ru",Boolean.TRUE},
                {"abc@gmail.com,asd@gmail.com,abc@gmail.com",Boolean.TRUE},
                {"gmail@gmail.com,asd@gmail.com,abc@gmail.com",Boolean.TRUE}, // gmail as name in first email
                {"abc@gmail.com,asd@gmail.ru,abc@gmail.com",Boolean.FALSE}, // .ru in second email
                {"abc@mail.ru,asd@gmail.com,abc@gmail.com",Boolean.FALSE}, // mail,ru in first email
        };
    }


    @BeforeMethod
    void setUp (){
        testsMethods = new TestsMethods();
    }

    @Test(dataProvider = "lowerThat100", description = "Value in string is lover that 100.99 ")
    void  value100 (String testValue, Boolean expectedResult){
        Assert.assertEquals(testsMethods.lowerThat100(testValue),expectedResult);
    }
    @Test(dataProvider = "domainInEmail", description = "All emails are on domains gmail.com or yandex.ru ")
    void  domainInEmail (String testValue, Boolean expectedResult){
        Assert.assertEquals(testsMethods.validEmailAccount(testValue),expectedResult);
    }
    @AfterMethod
    void afterM(ITestResult testResult){
        if (!testResult.isSuccess()){
            System.out.println(testResult.getMethod().getDescription() + "Failed with\n data: " +
                    testResult.getParameters()[0]+ "\n expected result: " + testResult.getParameters()[1]);
        }
    }

}
