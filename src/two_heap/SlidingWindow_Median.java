package two_heap;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class SlidingWindow_Median {
    // [1,3,-1,-3,5,3,6,7] => [{-1,1,3}=>1.0,{-3,-1,3}-1.0,{-3,-1,5}-1.0,{-3,3,5}3.0,5.0,6.0]
    public double[] findSlidingWindowMedians(int[] nums, int window){
        //create the result array and assign the size
        double[] result = new double[1 + nums.length - window];
        //create 2 Heap to keep the data and calculate the median
        PriorityQueue<Integer> smallNumbers = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> largeNumbers = new PriorityQueue<>(); //by default asceding order
        //if window is odd then we have to return small numbers top element else add both and divide by 2
        boolean oddNumbers = false;
        if(window%2 == 1){
            oddNumbers = true;
        }
        // assign all both Heap with first window values
        for(int i=0; i<window; i++){
            if(smallNumbers.size() <= largeNumbers.size()){
                largeNumbers.offer(nums[i]);
                smallNumbers.offer(largeNumbers.poll());
            }else{
                smallNumbers.offer(nums[i]); //to make it simple add in small then poll from small
                largeNumbers.offer(smallNumbers.poll());
            }
        }
        // assign the first element into the result
        if(oddNumbers){
            result[0] = smallNumbers.peek();
        }else{
            result[0] = (double)(smallNumbers.peek() + largeNumbers.peek()) /2;
        }
        for(int i=window; i<nums.length; i++){
            if(smallNumbers.contains(nums[i-window])){
                smallNumbers.remove(Integer.valueOf(nums[i-window])); //before add we have to remove first element
                largeNumbers.offer(nums[i]); // as we have to add in small better we put it in large and then poll
                smallNumbers.offer(largeNumbers.poll()); //this will give me smallest element from large
            }else{
                largeNumbers.remove(Integer.valueOf(nums[i-window]));
                smallNumbers.offer(nums[i]);
                largeNumbers.offer(smallNumbers.poll());
            }
            if(oddNumbers){
                result[1+i-window] = smallNumbers.peek();
            }else{
                result[1+i-window] = (double)(smallNumbers.peek() + largeNumbers.peek()) /2;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,3,-1,-3,5,3,6,7};
        System.out.println("Expected result = [1.0,-1.0,-1.0,3.0,5.0,6.0] and Actual = "+
                Arrays.toString(new SlidingWindow_Median().findSlidingWindowMedians(nums1, 3)));
        int[] nums2 = {1,2,3,4,2,3,1,4,2};
        System.out.println("Expected result = [2.0,3.0,3.0,3.0,2.0,3.0,2.0] and Actual = "+
                Arrays.toString(new SlidingWindow_Median().findSlidingWindowMedians(nums2, 3)));
    }
}
