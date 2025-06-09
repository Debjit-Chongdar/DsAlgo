package range_query;

import java.util.Arrays;

//Multiplication of all element except self
public class ProductOfArrayExceptSelf {
    // multiplication of all element except current position element
    //don't use divide
    public static int[] productOfArray_prefix_postfix(int[] nums){
        //if we can calculate prefix and postfix of each position, then we can multiply between them to get result
        //       [1,2,4,6]
        // pre=> [1,1,2,8]
        // post=>[48,24,6,1]  result =>[48, 24, 12, 8]
        int[] prefixMul = new int[nums.length];
        int[] postfixMul = new int[nums.length];
        prefixMul[0] = 1; //1 as it's going to use in multiplication, for sum it will be 0
        postfixMul[nums.length-1] = 1;
        for(int i=1; i<nums.length;i++){
            prefixMul[i] = prefixMul[i-1] * nums[i-1];
        }
        for(int j=nums.length-2; j>=0; j--){
            postfixMul[j] = postfixMul[j+1] * nums[j+1];
        }
        int[] result = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            result[i] = prefixMul[i] * postfixMul[i];
        }
        return result;
    }
    //we can optimize little bit
    public static int[] productOfArray_optiized(int[] nums){
        int[] postfixMul = new int[nums.length];
        postfixMul[nums.length-1] = 1;
        for(int j=nums.length-2; j>=0; j--){
            postfixMul[j] = postfixMul[j+1] * nums[j+1];
        }
        int[] result = new int[nums.length];
        int preVal = 1;
        for(int i=0; i<nums.length; i++){
            result[i] = preVal * postfixMul[i];
            preVal = preVal * nums[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,4,6};
        System.out.println(Arrays.toString(productOfArray_prefix_postfix(nums)));
        System.out.println(Arrays.toString(productOfArray_optiized(nums)));
        int[] arr = {-1, 0, 1, 2, 3};
        System.out.println(Arrays.toString(productOfArray_prefix_postfix(arr)));
        System.out.println(Arrays.toString(productOfArray_optiized(arr)));
    }
}
