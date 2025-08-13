package DynamicProgramming;

//Robber can't steal from two adjacent houses
//if steal from 2nd house then except 1st and 3rd, it can steal from any houses
// [5,3,4,11,2] => max output => [5,11]
public class HouseRobber {
    public static int maxValue(int[] nums){
        int[] dp = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            int cur = nums[i];
            // max value till previos house(adjacent is not allawed) + current
            if(i-2 >= 0){
                cur = cur + dp[i-2];
            }
            if(i-1 >= 0){ // max value betwwen previous house vs current house
                cur = Math.max(cur, dp[i-1]);
            }
            dp[i] = cur;
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        System.out.println("Max value from [6,7,1,3,8,2,4] : "+maxValue(new int[]{6,7,1,3,8,2,4}));
        System.out.println("Max value from [5,3,4,11,2] : "+maxValue(new int[]{5,3,4,11,2}));
    }
}
