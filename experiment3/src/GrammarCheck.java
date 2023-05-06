import java.util.ArrayList;
import java.util.List;

public class GrammarCheck {
    static void check(String input){
        Parser parser = new Parser();
        List<Symbol> inputList ;

        Morphology morphology = new Morphology();
        inputList = morphology.checkString(input);
        parser.check(inputList);
    }

    public static void main(String[] args) {
    }
}
