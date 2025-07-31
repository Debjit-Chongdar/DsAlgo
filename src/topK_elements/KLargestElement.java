package topK_elements;

import java.util.Arrays;
import java.util.PriorityQueue;

public class KLargestElement {
    // if we use MaxHeapApproach then heap size will be equal to array size
    // instead we will use heap size of K , then heap will take less time with minHeap
    public static int[] findTopK_Element(int[] nums, int k){
        int[] result = new int[k];
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> (a-b));
        for(int num : nums){
            minHeap.offer(num);
            while(minHeap.size() > k){
                minHeap.poll();
            }
        }
        for(int i=k-1; i>=0; i--){
            result[i] = minHeap.poll();
        }
        return result;
    }
    public static void main(String[] args) {
        int nums[] = {5,9,3,4,8,2};
        System.out.println(Arrays.toString(findTopK_Element(nums, 3)));
    }
}
