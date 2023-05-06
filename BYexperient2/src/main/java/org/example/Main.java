package org.example;

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
            Check check = new Check();
            while (x.hasNextLine()){
                check.checkString(x.nextLine());
        }
        }

        System.setOut(out);
    }
}
