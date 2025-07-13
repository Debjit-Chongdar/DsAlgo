package sliding_window.varialble_length;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubstringLength {

    //aabcdafcb => bcdaf = 5
    public static int lengthOfLongestSubstring(String str){
        if(str == null || str.isEmpty()){
            return -1;
        }
        int left=0;
        int maxLength = 0;
        List<Character> chars = new ArrayList<>();
        for(int right=0; right<str.length(); right++){
            while(chars.contains(str.charAt(right))){
                chars.remove(Character.valueOf(str.charAt(left)));
                left++;
            }
            chars.add(str.charAt(right));
            maxLength = Math.max(maxLength, 1+right-left);
            //maxLength = Math.max(maxLength, chars.size());
        }
        return maxLength;
    }
    //optimum solution
    public static int lengthOfLongestSubstring_optimum(String s) {
        int l = 0;
        int r = 0;
        int maxLength = 0;
        //keeping the last index of char occurance
        Map<Character, Integer> charIndexMap = new HashMap<>();
        for( ; r<s.length(); r++){
            if(charIndexMap.containsKey(s.charAt(r))){
                // DVD, when r=2, l is still in 0'th position, we have to move l from 0 to 1 to calculate the length
                l = charIndexMap.get(s.charAt(r)) + 1;
            }
            charIndexMap.put(s.charAt(r), r);
            maxLength = Math.max(1+r-l, maxLength);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aabcdafcb"));
        System.out.println(lengthOfLongestSubstring_optimum("aabcdafcb")); //giving wrong response
    }
}
