import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        PrintStream out = System.out; //保存原输出流

        for(int i=1;i<6;i++){
            String path = "D:\\test"+i+".txt";
            PrintStream ps = new PrintStream("D:\\result"+i+".txt");//新建新输出流
            System.setOut(ps);//切换输出流


            Scanner x = new Scanner(new FileReader(path));
            Morphology morphology = new Morphology();
            while (x.hasNextLine()){
                morphology.checkString(x.nextLine());
        }
        }

        System.setOut(out);
    }
}
