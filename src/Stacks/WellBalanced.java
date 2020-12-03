package Stacks;

public class WellBalanced {
    private Stack<Character> s;
    private String str;

    private static final String OPEN = "([{<";
    private static final String CLOSE = ">}])";

    WellBalanced(String s){
        this.str = s;
    }

    public boolean isWellBalanced(){
        return s.empty();
    }

    public static boolean isOpen(char ch){
        return OPEN.indexOf(ch) != -1;
    }
    public static boolean isClosing(char ch){
        return CLOSE.indexOf(ch) != -1;
    }
}
