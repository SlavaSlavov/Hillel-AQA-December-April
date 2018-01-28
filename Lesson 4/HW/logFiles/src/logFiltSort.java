import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class logFiltSort {
    public static void main(String[] args) {


        String filePath = "C:\\Users\\Slavik\\AppData\\Roaming\\Skype\\My Skype Received Files\\for test.txt";
        String inputSplit = "Log time";
        BufferedReader br;
        String[] words;
        String line;
        String[] sortedArray;
        List<String> containID = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(filePath));
            try {
                    while ((line = br.readLine()) != null) {
                    words = line.split("Log time: ");

                        for(String w:words) {
                            System.out.println(w);
                            if (w.contains("-")) {
                                containID.add(w);
                            }
                    }

                }
                System.out.println(containID);
                br.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }


    }
}
