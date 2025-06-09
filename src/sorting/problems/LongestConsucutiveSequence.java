package sorting.problems;

import java.util.*;

//[10,3,1,4,11,2,2] => [1,2,3,4] => 4
public class LongestConsucutiveSequence {
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
        for(int i=0; i<nums.length; i++){
            int size = 1+ numberPrePostVal.getOrDefault(nums[i]-1, 0)
                    + numberPrePostVal.getOrDefault(nums[i]+1, 0);
            numberPrePostVal.put(nums[i], size);
            maxConsecutiveNumber = Math.max(maxConsecutiveNumber, size);
        }
        return maxConsecutiveNumber;
    }

    public static void main(String[] args) {
        LongestConsucutiveSequence lcs = new LongestConsucutiveSequence();
        int[] nums = {2,20,4,10,3,4,5};
        System.out.println("Expected = "+ lcs.findLongestConsecutiveSequence(nums)+" # "+lcs.findLongestConsecutiveSequence_Optimized(nums));
        int[] nums1 = {0,3,2,5,4,6,1,1};
        ;System.out.println("Expected = "+ lcs.findLongestConsecutiveSequence(nums1)+" # "+lcs.findLongestConsecutiveSequence_Optimized(nums1));
    }
}
