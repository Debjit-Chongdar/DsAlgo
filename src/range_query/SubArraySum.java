package range_query;

import java.util.HashMap;
import java.util.Map;

public class SubArraySum {
    // find out all subArray having sum equal to k
    public static int subArraySum(int[] nums, int k){
        int result = 0;
        Map<Integer, Integer> prefixSumOccurance = new HashMap<>();
        int prefix = 0;
        prefixSumOccurance.put(prefix, 1); //0'th positions prefix sum is 0
        for(int i=0; i<nums.length; i++){
            if(prefixSumOccurance.containsKey(prefix-k)){
                result += prefixSumOccurance.get(prefix-k);
            }
            prefixSumOccurance.put(prefix, prefixSumOccurance.getOrDefault(prefix, 0)+1);
            prefix += nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, -1, 1, 2};
        System.out.println("Expected = 4, Actual = "+subArraySum(nums, 2));
        int[] arr = {4,4,4,4,4,4};
        System.out.println("Expected = 6, Actual = "+subArraySum(arr, 4));
    }
}
