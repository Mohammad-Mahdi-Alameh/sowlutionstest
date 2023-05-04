import java.util.*;

public class Solution {
    public static Boolean isValidBracketSequence(String str){
            Stack<Character> stack = new Stack<>();
            String line = str;
            for(char c : line.toCharArray()) {
                if(c == '{' || c == '(' || c == '[') {
                    stack.push(c);
                    continue;
                }
                if(c == '}' && !stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                    continue;
                }

                if(c == ')' && !stack.isEmpty() && stack.peek() == '('){
                    stack.pop();
                    continue;
                }

                if(c == ']' && !stack.isEmpty() && stack.peek() == '['){
                    stack.pop();
                    continue;
                }

                if(c == '}' || c == ')' || c == ']') {
                    stack.push(c);
                    break;
                }
            }
            return(stack.isEmpty());
        }

    public static void main(String[] args) {
       System.out.println(isValidBracketSequence("{[}]"));
    }
}
