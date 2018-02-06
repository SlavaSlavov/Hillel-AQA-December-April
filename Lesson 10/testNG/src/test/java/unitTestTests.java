import org.testng.Assert;
import org.testng.annotations.Test;

public class unitTestTests {

    //test data
    private static String testEmail = "abc@gmail.com,sdfs@gmail.com,abc@gmail.com,abc@gmail.com,asd@yandex.ru,abc@gmail.com";
    private static String testLower100 = "95";
    private static String testLower1450 = "2000";

    //test
    @Test
    private static void validEmailOK(){
        Assert.assertTrue(unitTest.validEmailAccount(testEmail));
    }
    @Test
    private static void validEmailKO(){
        Assert.assertFalse(unitTest.validEmailAccount(testEmail));
    }
    @Test
    private static void lower100KO(){
        Assert.assertFalse(unitTest.lowerThat100(testLower100));
    }
    @Test
    private static void lower100OK(){
        Assert.assertTrue(unitTest.lowerThat100(testLower100));
    }
    @Test
    private static void lower1450KO(){
        Assert.assertFalse(unitTest.lowerThat1450(testLower1450));
    }
    @Test
    private static void lower1450OK(){
        Assert.assertTrue(unitTest.lowerThat1450(testLower1450));
    }
}
