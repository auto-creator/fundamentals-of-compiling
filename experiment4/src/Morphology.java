import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Morphology {
    int index = 0;
    String token="";
    WordState state = WordState.START;
    List<Symbol> inputList;
    List<String> inputString;
    static List<String> keywords = new ArrayList<>();
    static HashMap<String, String> map = new HashMap<String, String>();
    static {
        try {
            Scanner sc1 = new Scanner(new FileReader("keywords.txt"));
            Scanner sc2;
            while (sc1.hasNextLine()) {
                keywords.add(sc1.nextLine());
            }
            sc1 = new Scanner(new FileReader("SymRef.txt"));
            sc2 = new Scanner(new FileReader("RealSym.txt"));
            while (sc1.hasNextLine()&&sc2.hasNextLine()) {
                map.put(sc2.nextLine(),sc1.nextLine());
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    List checkString(String s){
        inputList = new ArrayList<>();
        inputString = new ArrayList<>();
        index = 0;
        String negNote = "//.*$";
        s = s.replaceAll(negNote,"");
        s = s + "#";
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
                outPut();
                token = "";
                state = WordState.START;
                if(s.charAt(index)!=' ')
                    index--;
            }
            else if(s.charAt(index) != ' '){
                token += s.charAt(index);
            }

        }
        outPut();
        token = "";
        inputString.add("END");
        inputList.add(Symbol.END);
        return inputList;
    }
    void outPut() {
        inputString.add(token);
        switch (state)
        {
            case INTEND:
            case FLOATEND:
                inputList.add(Symbol.ALPHA);
                break;
            case WORDEND:
                if(keywords.contains(token)){
                    /**此处应当增加错误处理*/
                    System.out.println("(Keyword,"+token+")");
                }
                else
                    inputList.add(Symbol.ALPHA);
                break;
            case OPERATEEND:
                if(token.equals("+")||token.equals("-"))
                    inputList.add(Symbol.OPT1);
                else if(token.equals("*")||token.equals("/"))
                    inputList.add(Symbol.OPT2);
                break;
            case SYMBOLEND:
                /**此处应当增加错误处理*/
                if(token.equals("("))
                   inputList.add(Symbol.LPARN);
                else if(token.equals(")"))
                    inputList.add(Symbol.RPARN);
                else if(token.equals("+")||token.equals("-"))
                    inputList.add(Symbol.OPT1);
                else if(token.equals("*")||token.equals("/"))
                    inputList.add(Symbol.OPT2);
                break;
            default:
                /**此处应当增加错误处理*/
        }

    }


}


