
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Parser {
    // 用一个全局变量来存储当前输入的符号
    Stack<GRAMMAR> parsingStack = new Stack<GRAMMAR>();
    //static List<Symbol> input = new ArrayList<>();

    void check(List<Symbol> input){
        parsingStack.push(GRAMMAR.START);
        int index = 0;
        while(index<input.size()){
            stateChange(Symbol.valueOf(input.get(index)));

        }

    }

      int stateChange(Symbol sym) { //返回状态有三种：-1代表匹配失败，0代表匹配成功且是非终结符，1代表匹配成功且是终结符
          switch (parsingStack.peek()) {
              case START:
                  switch (sym) {
                      case ALPHA:
                      case LPARN:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.ADD0);
                          return 0;
                      default:
                          return -1;
                  }
              case ADD0:
                  switch (sym){
                      case ALPHA:
                      case LPARN:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.ADD1);
                          parsingStack.push(GRAMMAR.MUL0);
                          return 0;
                      default:
                          return -1;
                  }
              case ADD1:
                  switch (sym) {
                      case RPARN:
                      case END:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.BLANK);
                          return 0;
                      case OPT1:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.ADD1);
                          parsingStack.push(GRAMMAR.EXP);
                          parsingStack.push(GRAMMAR.OPT1);
                          return 0;
                      default:
                          return -1;
                  }

              case MUL0:
                  switch (sym){
                      case ALPHA :
                      case LPARN:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.MUL1);
                          parsingStack.push(GRAMMAR.EXP);
                          return 0;
                      default:
                          return -1;

                  }
              case MUL1:
                  switch (sym) {
                      case RPARN:
                      case OPT1:
                      case END:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.BLANK);
                          return 0;
                      case OPT2:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.MUL1);
                          parsingStack.push(GRAMMAR.EXP);
                          parsingStack.push(GRAMMAR.OPT2);
                          return 0;
                      default:
                          return -1;
                  }
              case EXP:
                  switch (sym) {
                      case ALPHA:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.ALPHA);
                          return 0;
                      case LPARN:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.RPARN);
                          parsingStack.push(GRAMMAR.ADD0);
                          parsingStack.push(GRAMMAR.LPARN);
                          return 0;
                      default:
                          return -1;
                  }
              case ALPHA:
              case LPARN:
              case RPARN:
                  return 1;
              case BLANK:
                  parsingStack.pop();
                  return 0 ;//此处返回值待定
              default:
                  return -1;
          }
      }



    }





}