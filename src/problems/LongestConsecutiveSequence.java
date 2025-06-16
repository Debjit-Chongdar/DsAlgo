package problems;

import java.util.*;

//[10,3,1,4,11,2,2] => [1,2,3,4] => 4
public class LongestConsecutiveSequence {
    public int findLongestConsecutiveSequence(int[] nums){
        Arrays.sort(nums); // => nLogN
        int maxSize = 0;
        int curSize = 1;
        for(int i=1; i<nums.length; i++) { // => n
            if(nums[i-1]+1 == nums[i]){
                curSize++;
            }else if(nums[i-1]+1 < nums[i]){
                curSize =1;
            }
            maxSize = Math.max(maxSize, curSize);
        }
        return maxSize;
    }
    //if we want to do it in BigO(n) time Complexity
    public int findLongestConsecutiveSequence_Optimized(int[] nums){
        Map<Integer, Integer> numberPrePostVal = new HashMap<>();
        int maxConsecutiveNumber = 0;
        for(int num: nums){
            if(!numberPrePostVal.containsKey(num)) {
                int size = 1 + numberPrePostVal.getOrDefault(num - 1, 0)
                        + numberPrePostVal.getOrDefault(num + 1, 0);
                numberPrePostVal.put(num, size);
                //if num-1 is not present then it will just put(num, size)
                //but if num-1 is present and it's value is 3 that means, num-2 is there and num-3 is the last, if repetadely num-2 come we are ignoring
                //just put(num-3, size) so that if any element come with num-4 it can use the data of num-3
                numberPrePostVal.put(num - numberPrePostVal.getOrDefault(num - 1, 0), size);
                numberPrePostVal.put(num + numberPrePostVal.getOrDefault(num + 1, 0), size);
                maxConsecutiveNumber = Math.max(maxConsecutiveNumber, size);
            }
        }
        return maxConsecutiveNumber;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence lcs = new LongestConsecutiveSequence();
        int[] nums = {2,20,4,10,3,4,5};
        System.out.println("Expected = "+ lcs.findLongestConsecutiveSequence_Optimized(nums)+" # "+lcs.findLongestConsecutiveSequence(nums));
        int[] nums1 = {0,3,2,5,4,6,1,1};
        System.out.println("Expected = " +lcs.findLongestConsecutiveSequence_Optimized(nums1) +" # "+ lcs.findLongestConsecutiveSequence(nums1));
    }
}
