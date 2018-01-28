public class regexValidEmails {
    public static void main(String[] args) {
        System.out.println(validEmailAccount("asadSbc@gmail.com,sdfs@gmail.com,abc@gmail.com,abc@gmail.com," +
                "asd@yandex.ru,abc@gmail.com,ab121412c@gmail.com,asd@yandex.ru,abc@gmail.com,abc@gmail.com," +
                "asd@yandex.ru,asdbc@gmail.com,1@gmail.com,ab121412c@gmail.com,asd@yandex.ru,abc@gmail.com," +
                "abc@gmail.com,asd@yandex.ru,abc@gmail.com")); //true

        System.out.println(validEmailAccount("abc@gmail.com,sdfs@gmail.com,abc@gmail.com,abc@gmail.com,asd@yandex.ru," +
                "abc@gmail.com,ab121412c@gmail.com,asd@yandex.ru,abc@gmail.com,abc@gmail.com,asd@yandex.ru,asdbc@gmail.com,1@gmail.com," +
                "ab121412c@gmail.com,asd@yandex.ua,abc@gmail.com,abc@gmail.com,asd@yandex.ru,abc@gmail.com")); //false (yandex.UA)
    }
    private static Boolean validEmailAccount(String emailsList){
        return emailsList.matches("^(?:.[^,\\s]+@(?:gmail.com|yandex.ru))+[,]?$");
    };
}
