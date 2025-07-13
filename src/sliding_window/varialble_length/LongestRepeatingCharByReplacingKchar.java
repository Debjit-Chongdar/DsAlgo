package sliding_window.varialble_length;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharByReplacingKchar {

    //longest repeating char by replacing k characters AAABABB , 1 -> AAAAABB(5)
    public static int characterReplacement(String s, int k) {
        int left = 0;
        int maxLength = 0;
        int maxFrequency = 0;
        Map<Character, Integer> charFrequency = new HashMap<>();
        for(int right = 0; right<s.length(); right++){
            charFrequency.put(s.charAt(right), charFrequency.getOrDefault(s.charAt(right), 0)+1);
            maxFrequency = Math.max(maxFrequency, charFrequency.get(s.charAt(right)));
            while(k < 1+right-left-maxFrequency){
                charFrequency.put(s.charAt(left), charFrequency.get(s.charAt(left))-1);
                left++;
            }
            maxLength = Math.max(maxLength, 1+right-left);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AAABABB", 1));
    }
}
