package problems;

import java.util.*;

public class ThreeSum {
    public static List<List<Integer>> threeSumResultingZero(int[] nums){
        // most crusial part is to sort first to optimize calculation
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for(int a=0; a<nums.length-2; a++){
            // if first element is itself greater than 0 then remaining next element's must be greater
            // so better not to check further and stop here.
            if(nums[a] > 0){
                break;
            }
            //if current position's value is same with last position then we will get duplicate in out put
            if(a > 0 && nums[a-1] == nums[a]){
                continue;
            }
            // second element of sum start from a + 1 position, right will be the last element
            int left = a+1;
            int right = nums.length-1;
            //until the remaining elements pointer meet each other
            while(left < right){
                int sum = nums[a] + nums[left] + nums[right];
                // As we sorted the nums array, last element has higher value
                if(sum > 0){
                    right --;
                }else if(sum < 0){ // in sorted array left pointer value is lesser than left+1
                    left++;
                }else{
                    //so we have a sum result is 0
                    result.add(Arrays.asList(nums[a] , nums[left] , nums[right]));
                    // As soon as get result we should move both pointer
                    left++;
                    right--;
                    //check any other left right combination with a resulting 0 or not
                    //if current left pointer value is equal to previous left pointer value then skip as we don't want repeat
                    while(left < right && nums[left] == nums[left-1]){
                        left++;
                    }
                }
            }
        }
        return result;
    }

    //two sum using map/set , easy approach
    public static List<List<Integer>> twoSumResultingZero(int[] nums){
        List<List<Integer>> result = new ArrayList<>();
        // <value, index> map if we want to return index combination
        // if we want only combination value then we can use List or Set
        Set<Integer> previousElements = new HashSet<>();
        for(int n : nums){
            int b = 0 - n;
            if(previousElements.contains(b)){
                result.add(Arrays.asList(b, n));
            }
            previousElements.add(n);
        }
        return result;
    }

    //two sum using two pointer concept, which used in three sum array
    public static List<List<Integer>> twoSumResultingZero1(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        int startIndex = 0;
        int endIndex = nums.length-1;
        while(startIndex < endIndex){
            int sum = (nums[startIndex] + nums[endIndex]);
            if(sum > 0){
                endIndex--;
            }else if(sum < 0){
                startIndex++;
            }else{
                result.add(Arrays.asList(nums[startIndex], nums[endIndex]));
                startIndex++;
                endIndex--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        System.out.println(threeSumResultingZero(nums));
        int[] nums1 = {0,1,1};
        System.out.println(threeSumResultingZero(nums1));

        System.out.println(twoSumResultingZero(nums));
        System.out.println(twoSumResultingZero(nums1));

        System.out.println(twoSumResultingZero1(new int[]{-1,0,1,2,-1,-4}));
    }
}
