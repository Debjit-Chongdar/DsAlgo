package prefix_sum;

public class PivotIndex {
    //find the index which sum of leftPart == sum of rightPart
    //BigO(n+n) = BigO(n)
    public static int findPivotIndex_prefixSum(int[] nums){
        int[] prefixSum = new int[nums.length];
        //populate prefixSum
        for(int i=0; i<nums.length; i++){
            if(i==0){
                prefixSum[i] = nums[i];
            }else{
                prefixSum[i] = prefixSum[i-1]+nums[i];
            }
        }
        //find the index
        for(int index=0; index<nums.length; index++){
            int leftSum = (index==0)? 0 : prefixSum[index-1];
            int rightSum = (index == nums.length-1)? 0 : prefixSum[nums.length-1] - prefixSum[index];
            if(leftSum == rightSum){
                return index;
            }
        }
        return -1;
    }
    //BigO(n)
    public static int findPivotIndex_twoPointer(int[] nums){
        int left =0;
        int right = nums.length-1;
        int leftTotal = nums[left];
        int rightTotal = nums[right];
        while(left+1 < right){
            if(left+1 == right-1 && leftTotal == rightTotal){
                return left+1;
            }
            if(leftTotal<rightTotal){
                left++;
                leftTotal += nums[left];
            }else{
                right--;
                rightTotal += nums[right];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {1,7,3,6,5,6};
        System.out.println("Expected index = 3 # Actual index = "+findPivotIndex_prefixSum(nums));
        System.out.println("Expected index = 3 # Actual index = "+findPivotIndex_twoPointer(nums));
        int[] arr = {1,2,3};
        System.out.println("Expected index = -1 # Actual index = "+findPivotIndex_prefixSum(arr));
        System.out.println("Expected index = -1 # Actual index = "+findPivotIndex_twoPointer(arr));
    }
}
