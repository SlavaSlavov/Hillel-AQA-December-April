public class URLCut {
    public static void main(String[] args){
//        cutTheUrl("https://github.com/SlavaSlavov/Hillel-AQA-December-April/");
    }
    private static void cutTheUrl(String givenUrl)  {
        String[] resultArr = givenUrl.split("/");
        System.out.println(resultArr[2]);
    }
}
