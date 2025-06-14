package permutation_combination;

import java.util.*;

public class CombinationOfPhNumber {
    static String[] map = {"", "", "abc","def", "ghi","jkl","mno","pqrs","tuv","wxyz"};
    //
    public static List<String> letterCombinations(String digits) {
        return combinations(digits, 0, "");
    }

    private static List<String> combinations(String digit, int index, String output){
        List<String> result = new ArrayList<>();
        if(digit.length() == index){ //till the index less than digit length, it has number pending to add
            result.add(output);
            return result;
        }
        //for each number add all possibilities to the output
        // digit[index] = 2, possibilies are a,b,c
        int mapPosition = digit.charAt(index)-'0';  // '2'-'0' = 2
        String tmp = map[mapPosition];
        for(int i=0; i<tmp.length(); i++){
            result.addAll(combinations(digit, index+1, output+tmp.charAt(i)));
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(letterCombinations("34"));
        System.out.println(letterCombinations(""));
        System.out.println(letterCombinations("345"));
    }
}
//                      (abc)
//        a             b               c
//      a(def)          b(def)          c(def)
//      ad  ae  af      bd be bf        cd ce cf