package combination;

import java.util.*;

public class CombinationOfPhNumber {
    static String[] map = {"", "", "abc","def", "ghi","jkl","mno","pqrs","tuv","wxyz"};
    //
    public static List<String> letterCombinations(String digits) {
        return combinations(digits, 0, "");
    }

    private static List<String> combinations(String digit, int index, String output){
        List<String> result = new ArrayList<>();
        if(digit.length() == index){
            result.add(output);
            return result;
        }
        for(int i=0; i<map[digit.charAt(index)-'0'].length(); i++){
            result.addAll(combinations(digit, index+1, output+map[digit.charAt(index)-'0'].charAt(i)));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("34"));
        System.out.println(letterCombinations(""));
        System.out.println(letterCombinations("345"));
    }
}
