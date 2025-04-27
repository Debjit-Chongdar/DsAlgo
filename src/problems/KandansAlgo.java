package problems;

public class KandansAlgo {

    //It can have +ve and -ve value together
    public static int maxSubArraySum(int[] arr){
        int maxVal = arr[0];
        int curVal = arr[0];
        for(int i=1; i<arr.length; i++){
            // current val vs previous+current
            curVal = Math.max(arr[i], curVal+arr[i]);
            // max vs cur val
            maxVal = Math.max(maxVal, curVal);
        }
        return maxVal;
    }

    public static int maxSubArraySum(int[][] arr){
        int[] lastArray = arr[0];
        int maxSum = maxSubArraySum(lastArray);
        int lastSum = maxSum;
        for(int i=1; i<arr.length; i++){
            //current row sum
            int curSum = maxSubArraySum(arr[i]);
            //combine last row and current row
            int[] combinedArray = mergeTwoArray(lastArray, arr[i]);
            int combinedSum = maxSubArraySum(combinedArray);
            // we have to keep last maxArray and sum val val of it
            if(combinedSum > curSum){
                lastArray = combinedArray;
                lastSum = combinedSum;
            }else{
                lastArray = arr[i];
                lastSum = curSum;
            }
            maxSum = Math.max(maxSum, lastSum);
        }
        return maxSum;
    }

    private static int[] mergeTwoArray(int[] arr1, int[] arr2){
        int[] result = new int[arr1.length];
        for(int i=0; i<result.length; i++){
            result[i] = arr1[i] + arr2[i];
        }
        return result;
    }

    public static void main(String[] args) {
        int arr[] = {2, 3, -8, 7, -1, 2, 3};
        System.out.println("Expected val = 11 , and actual = "+maxSubArraySum(arr));
        int arr1[] = {-2, -4};
        System.out.println("Expected val = -2 , and actual = "+maxSubArraySum(arr1));
        int arr2[] = {5, 4, 1, 7, 8};
        System.out.println("Expected val = 25 , and actual = "+maxSubArraySum(arr2));
        // test 2D metrics max sum
        int[][] mat  =  {{1, 2,-1,-4,-20},
                        {-8,-3, 4, 2, 1},
                        { 3, 8,10, 1, 3},
                        {-4,-1,1,  7,-6}};
        System.out.println("Expected val = 29, actual = "+maxSubArraySum(mat));
    }
}
