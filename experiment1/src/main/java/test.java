import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class test {
    List<String> keywords = new ArrayList<>();
    public static void main(String[] args) {
        IdentifierCounter idCounter = new IdentifierCounter();
        try {
            String fileName = "";
            for(int i = 1;i<=5;i++) {
                fileName = "D:\\test"+i+".txt";
                Scanner sc = new Scanner(new FileReader(fileName));
                test t = new test();
                while (sc.hasNextLine()) {
                    idCounter.Identify(sc.nextLine());
                }
                System.out.println(idCounter.map);
                idCounter.clear();
            }
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }


}