public class UnitTest {
    public static void main(String[] args) {
        validEmailKO();
        validEmailOK();
        lower100OK();
        lower100KO();
        lower1450OK();
        lower1450KO();
    }


    //test data
    private static String testEmail = "abc@gmail.com,sdfs@gmail.com,abc@gmail.com,abc@gmail.com,asd@yandex.ru,abc@gmail.com";
    private static String testLower100 = "95";
    private static String testLower1450 = "2000";


    //test
    private static void validEmailOK(){
        if (validEmailAccount(testEmail)) System.out.println("OK");
    }
    private static void validEmailKO(){
        if (!validEmailAccount(testEmail)) System.out.println("KO");
    }
    private static void lower100KO(){
        if (!lowerThat100(testLower100)) System.out.println("KO");
    }
    private static void lower100OK(){
        if (lowerThat100(testLower100)) System.out.println("OK");
    }
    private static void lower1450KO(){
        if (!lowerThat1450(testLower1450)) System.out.println("KO");
    }
    private static void lower1450OK(){
        if (lowerThat1450(testLower1450)) System.out.println("OK");
    }

    //methods
    private static Boolean validEmailAccount(String emailsList){
        return emailsList.matches("^(?:.[^,\\s]+@(?:gmail.com|yandex.ru))+[,]?$");
    }
    private static Boolean lowerThat100(String givenNumber){
        return givenNumber.matches("^(?:Amount: )?(?:100|\\d{1,2})(?:\\.\\d{2})?$");
    }
    private static Boolean lowerThat1450(String givenNumber){
        return givenNumber.matches("^(?:Amount: )?(?:1[0-4][0-4][0-9]|\\d{1,3}|1450)(?:\\.\\d{2})?$");
    }
}
