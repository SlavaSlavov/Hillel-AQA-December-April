public class TestsMethods {

    public TestsMethods(){}

    public Boolean validEmailAccount(String emailsList){
        return emailsList.matches("^(?:.[^,\\s]+@(?:gmail.com|yandex.ru))+[,]?$");
    }
    public Boolean lowerThat100(String givenNumber){
        return givenNumber.matches("^(?:Amount: )?(?:100|\\d{1,2})(?:\\.\\d{2})?$");
    }
    public Boolean greaterThat1450(String givenNumber){
        return givenNumber.matches("^(?:Amount: )?(?:14[5-9]\\d|1[5-9]\\d{2}|[2-9]\\d{3}|\\d{5,})(?:\\.\\d{2})?$");
    }



}
