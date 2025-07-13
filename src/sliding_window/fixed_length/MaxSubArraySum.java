package sliding_window.fixed_length;

public class MaxSubArraySum {
    //[8,3,-2,4,5,-1,0,5,3,9,-6] window =5 result = 18
    public static int maxSubArraySum(int[] nums, int window){
        int last = Math.min(nums.length, window);
        int sum = 0;
        for(int i=0; i<last; i++){
            sum += nums[i];
        }
        int maxSum = sum;
        int left = 0;
        for(int right=window; right<nums.length; right++){
            sum = sum - nums[left];
            left++;
            sum += nums[right];
            maxSum = Math.max(sum, maxSum);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        System.out.println(maxSubArraySum(new int[]{8,3,-2,4,5,-1,0,5,3,9,-6}, 5));
    }
}
