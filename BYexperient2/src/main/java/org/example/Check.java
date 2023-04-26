package org.example;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Check {
    int index = 0;
    String token="";
    WordState state = WordState.START;
    static List<String> keywords = new ArrayList<>();
    static HashMap<String, String> map = new HashMap<String, String>();
    static {
        try {
            Scanner sc1 = new Scanner(new FileReader("D:\\keywords.txt"));
            Scanner sc2;
            while (sc1.hasNextLine()) {
                keywords.add(sc1.nextLine());
            }
            sc1 = new Scanner(new FileReader("D:\\SymRef.txt"));
            sc2 = new Scanner(new FileReader("D:\\RealSym.txt"));
            while (sc1.hasNextLine()&&sc2.hasNextLine()) {
                map.put(sc2.nextLine(),sc1.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    void checkString(String s){
        index = 0;
        String negNote = "//.*$";
        s = s.replaceAll(negNote,"");
        for (;index<s.length();index++)
        {
//            if(s.charAt(index)==' ')
//            {
//                state = WordState.
//                outPut();
//                token = "";
//                state = WordState.START;
//                continue;
//            }
            state = WordState.transfer(state,s.charAt(index));
            if(state.compareTo(WordState.WORDEND)>=0){
                try {
                    outPut();
                }
                catch (IOException e){
                    e.printStackTrace();
                }

                token = "";
                state = WordState.START;
                if(s.charAt(index)!=' ')
                    index--;
            }
            else if(s.charAt(index) != ' '){
                token += s.charAt(index);
            }

        }
    }
    void outPut() throws FileNotFoundException {

        switch (state)
        {
            case INTEND:
            case FLOATEND:
                System.out.println("(Number,"+token+")");
                break;
            case WORDEND:
                if(keywords.contains(token))
                    System.out.println("(Keyword,"+token+")");
                else
                    System.out.println("(Identifier,"+token+")");
                break;
            case OPERATEEND:
                System.out.println("("+map.get(token)+","+token+")");
                break;
            case SYMBOLEND:
                if(!map.containsKey(token))
                    System.out.println("(Symbol,"+token+")");
                else
                    System.out.println("("+map.get(token)+","+token+")");
                break;
            default:
                System.out.println(state);
        }

    }


}


