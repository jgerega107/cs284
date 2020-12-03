package Stacks;

public class PalindromeChecker {
	// data fields
	private Stack<Character> s = new Stack<>();
	private String str;
	
	// Constructor
	PalindromeChecker(String s) {
		fillStack(s);
		this.str=s;
	}
	
	private void fillStack(String str) {
		// consider using s.CharAt(i) to grab i-th char in s
        for(int i = 0; i < str.length(); i++){
            s.push(str.charAt(i));
        }
	}
	
	private String reverseString() {
		StringBuilder sb = new StringBuilder();
		while(!s.empty()){
            sb.append(s.pop());
        }
		return sb.toString();
	}
	
	public Boolean isPalindrome() {
		return str.replaceAll("\\s+", "").equalsIgnoreCase(reverseString().replaceAll("\\s+", ""));
	}
	
	public static void main(String[] args) {
		// code to test your solution 
		// include examples and non-examples
        PalindromeChecker pc = new PalindromeChecker("kayak");
        System.out.println(pc.reverseString());

        System.out.println(pc.isPalindrome());
	}
}
