package prefix_sum;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumDivisibleByK {

    public static int subArraySumDivisibleByK(int[] nums, int k){
        int sum = 0;
        int count = 0;
        Map<Integer, Integer> modCount = new HashMap<>();
        modCount.put(0, 1);
        // time complexity  =  Big O(n)
        for (int num : nums){
            sum += num;
            int mod = sum % k;
            // Handle the negative mod to keep them in the range
            if(mod < 0){
                mod += k;
            }
            // count of sub array ending here with mod 0
            count += modCount.getOrDefault(mod, 0);
            // update the mod count
            modCount.put(mod, modCount.getOrDefault(mod, 0)+1);
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {4,5,0,-2,-3,1};
        int k=5;
        System.out.println(subArraySumDivisibleByK(nums, k));
    }
}
