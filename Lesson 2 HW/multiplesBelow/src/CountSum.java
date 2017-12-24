public class CountSum {
    public static void main(String[] args) {
       System.out.println(countSumOfMultipleOfTwoNumbers(30));
    }

    private static int countSumOfMultipleOfTwoNumbers (int inputValue){
        int result = 0;
        int FIRST_NUMBER =3;
        int SECOND_NUMBER =5;
        for (int i=0; i<=inputValue;i+=FIRST_NUMBER) result+=i;
        for (int y=0; y<=inputValue;y+=SECOND_NUMBER) if (y%FIRST_NUMBER!=0) result+=y;;

        return result;
    }

}
