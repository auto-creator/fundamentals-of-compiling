
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Parser {
    // 用一个全局变量来存储当前输入的符号
    Stack<GRAMMAR> parsingStack = new Stack<GRAMMAR>();
    static List<Symbol> input = new ArrayList<>();

    void check(List<String> input){
        parsingStack.push(GRAMMAR.START);
        int index = 0;

    }

      boolean start(Symbol sym){
        switch (sym){
            case ALPHA:
            case LPARN:
                parsingStack.push(GRAMMAR.ADD0);
                return true;
            default:
                return false;
        }
    }





}