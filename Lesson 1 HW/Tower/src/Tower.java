public class Tower  {

    public static void main(String[] args) {
//        createTower(12);
    }

    private static void createTower(int inputValue) {
        String synmbol = "#";
        String paddingLeft = " ";

        while (paddingLeft.length()<inputValue){
            paddingLeft = paddingLeft.concat(" ");
        }

        for (int i = 0; i < inputValue; i++){
            synmbol+="#";
            paddingLeft = paddingLeft.substring(0, paddingLeft.length() - 1);
            System.out.println(paddingLeft + synmbol + " " + synmbol);
        }

    }

}