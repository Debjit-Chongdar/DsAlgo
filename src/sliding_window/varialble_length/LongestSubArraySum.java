package sliding_window.varialble_length;

public class LongestSubArraySum {

    //[4,5,2,0,1,8,12,3,6,9]  k=15 , result = [4,5,2,0,1] = 5
    public static int longestSubArraySumLessthanK(int[] nums, int k){
        int length = -1;
        if(nums.length == 0){
            return length;
        }
        int left = 0;
        int val = 0;
        for(int right=0; right<nums.length; right++){
            val +=nums[right];
            while (val >= k){
                val = val- nums[left++];
            }
            length = Math.max(length, 1+right-left); // if right 2 and left 1 then length = 2
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(longestSubArraySumLessthanK(new int[]{4,5,2,0,1,8,12,3,6,9}, 15));
    }
}
