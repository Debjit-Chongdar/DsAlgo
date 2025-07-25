package MonotonicStack;

import java.util.Arrays;
import java.util.Stack;

// [5,2,8,4,3,1] => [8,8,-1,-1,-1,-1]
// [2,1,5,6,2,3] => [5,5,6,-1,3,-1]
public class NextHigherElement {
    // using brute force approach, time complexity O(n^2) and space compexity = O(1)
    public static int[] findNextHigherElement(int[] nums){
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        //we will use decreasing monotonic stack
        Stack<Integer> monotonicStack = new Stack<>();
        for(int i=0; i<nums.length; i++){
            while(!monotonicStack.isEmpty() && nums[monotonicStack.peek()] < nums[i]){
                int index = monotonicStack.pop();
                result[index] = nums[i];
            }
            monotonicStack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findNextHigherElement(new int[]{5,2,8,4,3,1})));
        System.out.println(Arrays.toString(findNextHigherElement(new int[]{2,1,5,6,2,3})));
    }
}
