package problems;

import java.util.*;

public class SlidingWindow {
    //Brute force approach for fixed length sliding window
    public static int maximumSubArraySum_bruteForce(int[] numbers, int k){
        int max = Integer.MIN_VALUE;
        for(int i=0; i+k-1<numbers.length; i++){
            int last = i+k;
            int sum = 0;
            for(int j=i; j<last; j++){
                sum += numbers[j];
            }
            max = Math.max(max, sum);
        }
        return max;
    }
    //Sliding window of fixed size, Time save approach Dynamic programming
    public static int maximumSubArraySum_dp(int[] numbers, int k){
        //calculate first k element's sum
        int end = Math.min(numbers.length, k);
        int sum = 0;
        for(int i=0; i<end; i++){
            sum += numbers[i];
        }
        //populate max sum
        int maxSum = sum;
        //max sum from each window starting from end of the first window
        for(int i=k; i<numbers.length; i++){
            sum = sum + numbers[i] - numbers[i-k];
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }
    //Sliding variable/dynamic window with Dynamic programming
    public static int longestSubarraySumLessThanK(int[] numbers, int k){
        int left = 0;
        int right = 0;
        int maxLength = -1;
        int sum = 0;
        for( ; right<numbers.length; right++){
            sum += numbers[right];
            while(sum>=k && left < right){
                sum = sum - numbers[left];
                left++;
            }
            //if 0 and 1 then we have to return 2 not (1-0)=1
            maxLength = Math.max(maxLength, 1+right-left);
        }
        return maxLength;
    }
    //Sliding window with dynamic value
    public static int lengthOfLongestSubstring(String s) {
        List<Character> chars = new ArrayList<>();
        int left=0;
        int right=0;
        int maxLength=0;
        char[] chs = s.toCharArray();
        for( ; right<chs.length; right++){
            while(chars.contains(chs[right])){
                chars.remove(Character.valueOf(chs[left]));
                left++;
            }
            chars.add(chs[right]);
            maxLength = Math.max(maxLength, 1+right-left);
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
    //longest repeating char by replacing k characters AAABABB , 1 -> AAAAABB(5)
    public static int characterReplacement(String s, int k) {
        int left=0;
        int right=0;
        int max = 0;
        int maxFrequency = 0;
        Map<Character, Integer> map = new HashMap<>();
        for( ; right<s.length(); right++){
            map.put(s.charAt(right), map.getOrDefault(s.charAt(right), 0)+1);
            maxFrequency = Math.max(maxFrequency, map.get(s.charAt(right)));
            while(k< 1+right-left-maxFrequency){
                map.put(s.charAt(left), map.get(s.charAt(left))-1);
                left++;
            }
            max = Math.max(max, 1+right-left);
        }
        return max;
    }
    // if s1 permutation present in s2 then return true else false
    // ("lab","lecabee") => false , ("ace","lecabee") => true
    static public boolean characterReplacement(String s1, String s2) {
        if(s1.length() > s2.length()){
            return false;
        }
        int[] s1Arr = new int[1+'z' - 'a'];
        for(char c:s1.toCharArray()){
            s1Arr[c-'a'] = s1Arr[c-'a']+1;
        }
        //check first match from s2
        int[] target = new int[s1Arr.length];
        for(int i=0; i<s1.length(); i++){
            target[s2.charAt(i)-'a'] = target[s2.charAt(i)-'a']+1;
        }
        if(Arrays.equals(s1Arr, target)){
            return true;
        }
        for(int i=s1.length(); i<s2.length(); i++){
            target[s2.charAt(i)-'a'] = target[s2.charAt(i)-'a']+1;
            target[s2.charAt(i-s1.length())-'a'] = target[s2.charAt(i-s1.length())-'a']-1;
            if(Arrays.equals(s1Arr, target)){
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        int[] numbers = {8,3,-2,4,5,-1,0,5,3,9,-6};
        System.out.println(maximumSubArraySum_bruteForce(numbers, 5));
        System.out.println(maximumSubArraySum_dp(numbers, 5));

        int[] n = {4,5,2,0,1,8,12,3,6,9};
        System.out.println(longestSubarraySumLessThanK(n, 15));

        System.out.println(lengthOfLongestSubstring("DVDF"));
        System.out.println(lengthOfLongestSubstring_optimum("DVDF"));
        System.out.println(lengthOfLongestSubstring_optimum("zxyzxyz"));

        System.out.println(characterReplacement("AAABABB", 1));
        System.out.println(characterReplacement("AABBBABABB", 1));

        System.out.println(characterReplacement("lab","lecabee"));
    }
}
