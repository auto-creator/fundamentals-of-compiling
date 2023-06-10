
import java.util.List;
import java.util.Stack;

public class Parser {
    // 用一个全局变量来存储当前输入的符号
    Stack<GRAMMAR> parsingStack = new Stack<GRAMMAR>();
    List<String> inputString;
    Stack<Identifier> IdentifierStack = new Stack<>();
    int Place = 0;
    //static List<Symbol> input = new ArrayList<>();

    boolean check(List<Symbol> input,List<String> inputString){
        IdentifierStack.clear();
        this.inputString = inputString;
        parsingStack.push(GRAMMAR.START);
        int index = 0;
        while(index<input.size()&&!parsingStack.empty()){
            if (!parsingStack.empty()) {
            }
            int flag = stateChange(input.get(index),inputString.get(index));
            if(flag==-1) {
                System.out.println("没有可用的语法，匹配失败");
                return false;
            }
            else if(flag==0){//状态已经转变，可以继续下一次循环
                continue;
            }
            else //flag == 1，比较栈顶和输入符号
            {
                if(input.get(index).name().equals(parsingStack.peek().name())) {//说明栈顶和INPUT字符相等，可以消去
                    parsingStack.pop();
                    index++;
                }
                else {
                    System.out.println(input.get(index).name());
                    System.out.println("栈顶和INPUT字符不相等，匹配失败");
                    return false;
                }
            }

        }
        if(parsingStack.empty()&&index==input.size()-1&&input.get(index).name()=="END")
        {
            System.out.println("匹配成功,运算值为:"+ IdentifierStack.pop());
            return true;
        }
        else
        {
            System.out.println("未同时为空，匹配失败");
            return false;}

    }

      int stateChange(Symbol sym,String s) { //返回状态有三种：-1代表匹配失败，0代表匹配成功且是非终结符，1代表匹配成功且是终结符
          Identifier x,y;
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
                      case OPT_PLUS:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.ADD1);
                          parsingStack.push(GRAMMAR.PLUS);
                          parsingStack.push(GRAMMAR.MUL0);
                          parsingStack.push(GRAMMAR.OPT_PLUS);
                          return 0;
                      case OPT_MINUS:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.ADD1);
                          parsingStack.push(GRAMMAR.MINUS);
                          parsingStack.push(GRAMMAR.MUL0);
                          parsingStack.push(GRAMMAR.OPT_MINUS);
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
                      case OPT_MINUS:
                      case OPT_PLUS:
                      case END:
                          parsingStack.pop();
                          parsingStack.push(GRAMMAR.BLANK);
                          return 0;
                        case OPT_MUL:
                            parsingStack.pop();
                            parsingStack.push(GRAMMAR.MUL1);
                            parsingStack.push(GRAMMAR.MULTIPLE);
                            parsingStack.push(GRAMMAR.EXP);
                            parsingStack.push(GRAMMAR.OPT_MUL);
                            return 0;
                        case OPT_DIVIDE:
                            parsingStack.pop();
                            parsingStack.push(GRAMMAR.MUL1);
                            parsingStack.push(GRAMMAR.DIVIDE);
                            parsingStack.push(GRAMMAR.EXP);
                            parsingStack.push(GRAMMAR.OPT_DIVIDE);
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

              case PLUS:
                  x = IdentifierStack.pop();
                  y = IdentifierStack.pop();
                  System.out.println("("+"+"+","+y.place+","+x.place+","+"t"+Place+")");
                  IdentifierStack.push(new Identifier("t"+Place));
                  Place++;
                  parsingStack.pop();
                  return 0;
              case MINUS:
                  x = IdentifierStack.pop();
                  y = IdentifierStack.pop();
                  System.out.println("("+"-"+","+y.place+","+x.place+","+"t"+Place+")");
                  IdentifierStack.push(new Identifier("t"+Place));
                  Place++;
                  parsingStack.pop();
                  return 0;
              case MULTIPLE:
                  x = IdentifierStack.pop();
                  y = IdentifierStack.pop();
                  System.out.println("("+"*"+","+y.place+","+x.place+","+"t"+Place+")");
                  IdentifierStack.push(new Identifier("t"+Place));
                  Place++;
                  parsingStack.pop();
                  return 0;
                case DIVIDE:
                  x = IdentifierStack.pop();
                  y = IdentifierStack.pop();
                  System.out.println("("+"/"+","+y.place+","+x.place+","+"t"+Place+")");
                  IdentifierStack.push(new Identifier("t"+Place));
                  Place++;
                  parsingStack.pop();
                  return 0;
              case ALPHA:
                  IdentifierStack.push(new Identifier(s));
              case LPARN:
              case RPARN:
              case OPT_PLUS:
              case OPT_MINUS:
              case OPT_MUL:
              case OPT_DIVIDE:
                  return 1;
              case BLANK:
                  parsingStack.pop();
                  return 0 ;//此处返回值待定
              default:
                  return -1;
          }
      }



    }

class Identifier {
    public String place;
    Identifier(String place){
        this.place = place;
    }
    Identifier(){
        this.place = "";
    }
}







