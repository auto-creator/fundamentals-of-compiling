public class GrammarCheck {
    static void check(String input){
        Morphology morphology = new Morphology();
        morphology.checkString(input);
    }

    public static void main(String[] args) {
        GrammarCheck.check("1 + 1 =  a");
    }
}
