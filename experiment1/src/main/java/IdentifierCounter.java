import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class IdentifierCounter {
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    static List<String> keywords = new ArrayList<>();
    static {
        try {
            Scanner sc = new Scanner(new FileReader("D:\\keywords.txt"));
            while (sc.hasNextLine()) {
                keywords.add(sc.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

   void Identify(String input){

//        Pattern c = Pattern.compile();
//        Pattern c = Pattern.compile();
        String negQuoatation = "\\\"([^\\\"]*)\\\"";
        String negNote = "//.*$";
        input = input.replaceAll(negNote,"");
        input = input.replaceAll(negQuoatation,"");

        Pattern pattern = Pattern.compile("[a-zA-Z_][a-zA-Z_0-9]*");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String identifier = matcher.group();
            if(keywords.contains(identifier))
                continue;
            if (this.map.containsKey(identifier)) {
                map.put(identifier, map.get(identifier) + 1);
            } else {
                map.put(identifier, 1);
            }
        }
    }

    public void clear(){
        this.map.clear();
    }

    public static void main(String[] args) {
        String input = "public class HelloWorld { public static void main(String[] args) { System.out.println(\"Hello, \"World!\"\"); } }";
    }
}