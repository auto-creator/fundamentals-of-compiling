import java.util.ArrayList;
import java.util.List;

public class GrammarCheck {
    static void check(String input){
        Parser parser = new Parser();
        List<Symbol> inputList ;
        List<String> inputString;
        boolean result;

        Morphology morphology = new Morphology();
        inputList = morphology.checkString(input);
        inputString = morphology.inputString;
        parser.inputString = inputString;
        result = parser.check(inputList,parser.inputString);
        if(!result){
            System.out.println("deny");
        }
        else
            System.out.println("accept");
    }

    public static void main(String[] args) {
    }
}
