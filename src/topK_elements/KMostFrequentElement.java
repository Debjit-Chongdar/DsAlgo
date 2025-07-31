package topK_elements;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class KMostFrequentElement {

    public static int[] topK_frequentElement(int[] nums, int k){
        int[] result = new int[k];
        Map<Integer, Integer> numFrequency = new HashMap<>();
        //populate map
        for(int num : nums){
            numFrequency.put(num, numFrequency.getOrDefault(num, 0)+1);
        }
        //Min heap based on value
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>
                ((e1, e2) -> e1.getValue()- e2.getValue());
        for(Map.Entry<Integer, Integer> entry : numFrequency.entrySet()){
            minHeap.offer(entry);
            while(minHeap.size() > k){
                minHeap.poll();
            }
        }
        for(int i = k-1; i>=0; i--){
            result[i] = minHeap.poll().getKey();
        }
        return result;
    }

    public static void main(String[] args) {
        int nums[] = {5,9,3,2,5,2,4,8,5,3,2,5};
        System.out.println(Arrays.toString(topK_frequentElement(nums, 3)));
    }
}
