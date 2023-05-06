import java.util.ArrayList;
import java.util.List;

public class GrammarCheck {
    static void check(String input){
        Parser parser = new Parser();
        List<Symbol> inputList ;
        boolean result;

        Morphology morphology = new Morphology();
        inputList = morphology.checkString(input);
        result = parser.check(inputList);
        if(!result){
            System.out.println("deny");
        }
        else
            System.out.println("accept");
    }

    public static void main(String[] args) {
    }
}
