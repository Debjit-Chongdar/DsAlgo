package prefixSum;

public class RangeSum {
    //Common approach is Segment tree but it takes n + log(n) time to populate + retrieve
    //with below approach we can get it in n + 1 time to populate and retrieve
    private int[] prefixSum;
    //This operation takes BigO(n)
    public void populatePrefixSum(int[] nums){
        prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for(int i=1; i<nums.length; i++){
            prefixSum[i] = prefixSum[i-1] + nums[i];
        }
    }
    //This operation takes BigO(1)
    public int sumOfRange(int left, int right){
        if(left >= 0 && right < prefixSum.length && right > left){
            //subtract the left's previous index value
            return prefixSum[right] - ((left==0) ? 0 : prefixSum[left-1]);
        }
        return -1;
    }

    // same but more easy approach by leaving 0'th index ------------------------------------------------------
    public void populatePrefixSumAlternative(int[] nums){
        prefixSum = new int[nums.length+1];
        for(int i=0; i<nums.length; i++){
            prefixSum[i+1] = prefixSum[i] + nums[i];
        }
    }
    public int sumOfRangeAlternative(int left, int right){
        if(left >= 0 && right < prefixSum.length-1 && right > left){
            return prefixSum[right+1] - prefixSum[left];
        }
        return -1;
    }

    public static void main(String[] args) {
        RangeSum rangeSum = new RangeSum();
        int[] nums = {-2,0,3,-5,2,-1};
        rangeSum.populatePrefixSum(nums);
        System.out.println("Expected = 1 : Actual = "+ rangeSum.sumOfRange(0,2));
        System.out.println("Expected = -1 : Actual = "+ rangeSum.sumOfRange(2,5));
        System.out.println("Expected = -3 : Actual = "+ rangeSum.sumOfRange(0,5));
        //using alternative approach
        rangeSum.populatePrefixSumAlternative(nums);
        System.out.println("Expected = 1 : Actual = "+ rangeSum.sumOfRangeAlternative(0,2));
        System.out.println("Expected = -1 : Actual = "+ rangeSum.sumOfRangeAlternative(2,5));
        System.out.println("Expected = -3 : Actual = "+ rangeSum.sumOfRangeAlternative(0,5));
    }
}
