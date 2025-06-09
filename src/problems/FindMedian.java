package problems;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

// [1,3,4] => 3  #  [1,2,4,5] => (2+4)/2 => 3
public class FindMedian {
    private List<Integer> nums;

    public FindMedian(){
        nums = new ArrayList<>();
    }
    public void add(int data){      //BigO => (1)
        nums.add(data);
    }
    public double median(){
        Collections.sort(nums); // BigO => nLog(n)
        int index = nums.size()/2;
        if(nums.size()%2 == 0){
            return (nums.get(index)+nums.get(index-1))/2;
        }else{
            return nums.get(index);
        }
    }
    //Optimized way to get median
    // try to keep both queue same element, for odd number any heap can have 1 extra element
    // small heap return value in desending order & large heap will return in ascending order
    private PriorityQueue<Integer> smallHeap = new PriorityQueue<>((a,b) -> b-a); //keep small half values in this queue
    private PriorityQueue<Integer> largeHeap = new PriorityQueue<>((a,b) -> a-b); //keep large half values in this queue

    public void add_heap(int data){
        if(smallHeap.isEmpty()){
            smallHeap.offer(data);
            return;
        }
        if(largeHeap.isEmpty()){ // it's mandatory to check if the value is lesser than small heap data or larger
            smallHeap.offer(data); // if lesser then that data shoud go smaller heap and smaller heap data should
            largeHeap.offer(smallHeap.poll()); // come to larger heap
            return;
        }
        //if small of the large is lesser than data then insert in large
        if(largeHeap.peek() < data){
            largeHeap.offer(data);
            if(smallHeap.size()+1 < largeHeap.size()){ // if large heap was already had 1 extra element
                smallHeap.offer(largeHeap.poll());
            }
        }else{ //if(smallHeap.peek() > data || smallHeap.peek() < data){
            smallHeap.offer(data);
            if(largeHeap.size()+1 < smallHeap.size()){ // if small heap had 1 element extra earlier
                largeHeap.offer(smallHeap.poll());
            }
        }
    }

    public double median_heap(){        // BigO => (1)
        if(smallHeap.isEmpty() && largeHeap.isEmpty()){
            return -0.0;
        }
        if(smallHeap.size() == largeHeap.size()){
            return (smallHeap.peek() + largeHeap.peek())/2;
        }else if(smallHeap.size() > largeHeap.size()){
            return smallHeap.peek();
        }else{
            return largeHeap.peek();
        }
    }

    public static void main(String[] args) {
        FindMedian findMedian = new FindMedian();
        findMedian.add(2);
        findMedian.add_heap(2);
        System.out.println(findMedian.median()+" == "+ findMedian.median_heap());
        findMedian.add(1);
        findMedian.add(4);
        findMedian.add_heap(1);
        findMedian.add_heap(4);
        System.out.println(findMedian.median()+" == "+ findMedian.median_heap());
        findMedian.add(6);
        findMedian.add_heap(6);
        System.out.println(findMedian.median()+" == "+ findMedian.median_heap());
    }
}
