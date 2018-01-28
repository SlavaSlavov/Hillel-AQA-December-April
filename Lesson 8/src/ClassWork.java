import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClassWork {
    public static void main(String[] args) {

        System.out.println(validEmailAccount("some@gmail.com,valid@yandex.ru some@gmail.com"));
        System.out.println(removeLastCharacterD("afasfasfada dsf sfsgs gsgds d"));
    }

    public static String removeLastCharacterD(String givenString){

         return givenString.replace("(.*)d","$1");
    }

    private static Boolean validEmailAccount(String emailsList){

        return emailsList.matches("^(.[^,\\s]+@(gmail.com|yandex.ru))+[,]?$");
    };
}
