
import java.util.Scanner;

public class Parser {
    // 用一个全局变量来存储当前输入的符号
    static char token;

    // 用一个Scanner对象来读取输入
    static Scanner scanner = new Scanner(System.in);

    // 用一个方法来获取下一个输入的符号，并存储在token中
    static void nextToken() {
        if (scanner.hasNext()) {
            token = scanner.next().charAt(0);
        } else {
            token = '$'; // 表示输入结束
        }
    }

    // 用一个方法来匹配当前输入的符号是否与期望的符号相同，如果是则获取下一个符号，否则报错
    static void match(char expected) {
        if (token == expected) {
            nextToken();
        } else {
            System.out.println("Syntax error: expected " + expected + " but found " + token);
            System.exit(1);
        }
    }

    // 用一个方法来实现E的子程序，即匹配E -> T | E + T | E - T
    static void E() {
        T(); // 匹配T
        while (token == '+' || token == '-') { // 如果当前符号是+或-
            match(token); // 匹配+或-
            T(); // 匹配T
        }
    }

    // 用一个方法来实现T的子程序，即匹配T -> F | T * F | T / F
    static void T() {
        F(); // 匹配F
        while (token == '*' || token == '/') { // 如果当前符号是*或/
            match(token); // 匹配*或/
            F(); // 匹配F
        }
    }

    // 用一个方法来实现F的子程序，即匹配F -> (E) | num
    static void F() {
        if (token == '(') { // 如果当前符号是(
            match('('); // 匹配(
            E(); // 匹配E
            match(')'); // 匹配)
        } else if (Character.isDigit(token)) { // 如果当前符号是数字
            match(token); // 匹配数字
        } else {
            System.out.println("Syntax error: expected ( or num but found " + token);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        nextToken(); // 获取第一个输入的符
        E(); // 调用E的子程序开始分析
        if (token == '$') { // 如果分析结束后当前符号是$
            System.out.println("Syntax OK"); // 输出语法正确
        } else {
            System.out.println("Syntax error: expected $ but found " + token); // 输出语法错误
        }
    }
}