package two_heap;

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
        if(smallHeap.size() < largeHeap.size()){ // if smallHeap has less data then we have to add in small heap
            largeHeap.offer(data);
            smallHeap.offer(largeHeap.poll());
        }else{
            smallHeap.offer(data);
            largeHeap.offer(smallHeap.poll());
        }
    }

    public double median_heap(){        // BigO => (1)
        if(smallHeap.isEmpty() && largeHeap.isEmpty()){
            return -0.0;
        }
        if(smallHeap.size() == largeHeap.size()){
            return (smallHeap.peek() + largeHeap.peek())/2;
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
