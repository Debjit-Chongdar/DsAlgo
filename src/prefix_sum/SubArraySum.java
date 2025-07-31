package prefix_sum;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {
    // find out all subArray having sum equal to k
    public static int subArraySum(int[] nums, int k){
        int count = 0;
        int prefixSum = 0;
        Map<Integer, Integer> prefixSumOccurance = new HashMap<>();
        prefixSumOccurance.put(0, 1); //0'th positions prefix sum is 0
        for(int num : nums){
            prefixSum += num;
            if(prefixSumOccurance.containsKey(prefixSum-k)){
                count += prefixSumOccurance.get(prefixSum-k);
            }
            prefixSumOccurance.put(prefixSum, prefixSumOccurance.getOrDefault(prefixSum, 0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {2, -1, 1, 2};
        System.out.println("Expected = 4, Actual = "+subArraySum(nums, 2));
        int[] arr = {4,4,4,4,4,4};
        System.out.println("Expected = 6, Actual = "+subArraySum(arr, 4));
    }
}
