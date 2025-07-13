package recursion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Parenthesis {
    //n=3 , ()()(), (())(), ()(()), (()()), ((()))
    public static List<String> createValidParenthesis(int n){
        return differentWay("", 0, 0, n);
    }
    private static List<String> differentWay(String s, int openCount, int closeCount, int total){
        List<String> ways = new ArrayList<>();
        if(openCount==total && closeCount==total){
            ways.add(s);
            return ways;
        }
        if(openCount < total){
            ways.addAll(differentWay(s+"(", openCount+1, closeCount, total));
        }
        if(closeCount < openCount){
            ways.addAll(differentWay(s+")", openCount, closeCount+1, total));
        }
        return ways;
    }

    static boolean isValidParenthesis(String str){
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<str.length(); i++){
            switch(str.charAt(i)){
                case '(' : stack.push(')');break;
                case '{' : stack.push('}'); break;
                case '[' : stack.push(']'); break;
                case ')' : if(stack.isEmpty() || (stack.pop() != ')')) return false; break;
                case '}' : if(stack.isEmpty() || (stack.pop() != '}')) return false; break;
                case ']' : if(stack.isEmpty() || (stack.pop() != ']')) return false; break;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        List<String> result = createValidParenthesis(3);
        result.forEach(System.out::println);

        System.out.println("true : "+ isValidParenthesis("[()()]{()}"));
        System.out.println("false : " + isValidParenthesis("{[(]})"));
    }
}
