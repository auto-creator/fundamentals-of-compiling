import java.util.ArrayList;
import java.util.List;

public class GrammarCheck {
    static void check(String input){
        List<Symbol> inputList ;

        Morphology morphology = new Morphology();
        inputList = morphology.checkString(input);
        System.out.println(inputList);
    }

    public static void main(String[] args) {
    }
}
