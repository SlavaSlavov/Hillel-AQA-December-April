public class unitTest {
        public static void main(String[] args) {
//            validEmailKO();
//            validEmailOK();
//            lower100OK();
//            lower100KO();
//            lower1450OK();
//            lower1450KO();
        }


        //methods
        public static Boolean validEmailAccount(String emailsList){
            return emailsList.matches("^(?:.[^,\\s]+@(?:gmail.com|yandex.ru))+[,]?$");
        }
        public static Boolean lowerThat100(String givenNumber){
            return givenNumber.matches("^(?:Amount: )?(?:100|\\d{1,2})(?:\\.\\d{2})?$");
        }
        public static Boolean lowerThat1450(String givenNumber){
            return givenNumber.matches("^(?:Amount: )?(?:1[0-4][0-4][0-9]|\\d{1,3}|1450)(?:\\.\\d{2})?$");
        }
    }


