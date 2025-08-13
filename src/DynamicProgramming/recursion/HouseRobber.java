package DynamicProgramming.recursion;

//Robber can't steal from two adjacent houses
//if steal from 2nd house then except 1st and 3rd, it can steal from any houses
// [5,3,4,11,2] => max output => [5,11]
public class HouseRobber {

    public static int maxValue(int[] nums){
        return maxVal(nums, 0);
    }
    private static int maxVal(int[] nums, int start){
        if(start >= nums.length){
            return 0;
        }
        // Robber can pick current house or can skip
        // if pick, then can not visit next house
        int pick = nums[start] + maxVal(nums, start+2);
        // if Robber wants to skip the current house
        int skip = maxVal(nums, start+1);
        return Math.max(pick, skip);
    }

    public static void main(String[] args) {
        System.out.println("Max value from [6,7,1,3,8,2,4] : "+maxValue(new int[]{6,7,1,3,8,2,4}));
        System.out.println("Max value from [5,3,4,11,2] : "+maxValue(new int[]{5,3,4,11,2}));
    }
}
