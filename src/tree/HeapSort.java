package tree;

import java.util.Arrays;

public class HeapSort {

    int[] createMaxHeap(int[] input){
        int[] result = new int[input.length];
        Arrays.fill(result, -1);
        for(int i=0; i<input.length; i++){
            insertMaxHeap(result, input[i], i);
        }
        return result;
    }
    void insertMaxHeap(int[] input, int val, int position){
        input[position] = val;
        int parent = (position-1)/2;
        while(parent >= 0){
            int child = parent*2+1;
            if(child+1<input.length && input[child+1]>input[child]){
                child = child+1;
            }
            if(input[parent] < input[child]){
                int tmp = input[child];
                input[child] = input[parent];
                input[parent] = tmp;
                if(parent == 0){
                    break;
                }else{
                    parent = (parent-1)/2;
                }
            }else{
                break;
            }
        }
    }
    public  void heapSort(int[] input){
        int lastIndex = input.length-1;
        for(int i = 0; i<lastIndex; i++) {
            int tmp = input[lastIndex - i];
            input[lastIndex - i] = input[0];
            input[0] = tmp;
            reArrange(input, 0,(lastIndex - 1) - i);
        }
    }

    private void reArrange(int[] input, int parentIndex, int lastIndex) {
        if(parentIndex >= lastIndex){
            return;
        }
        int child = parentIndex*2+1;
        if(child+1 <= lastIndex && input[child] < input[child+1]){
            child = child+1;
        }
        if (child <= lastIndex){
            if(input[parentIndex] < input[child]) {
                int tmp = input[child];
                input[child] = input[parentIndex];
                input[parentIndex] = tmp;
                reArrange(input, child, lastIndex);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {2,6,7,3,4,1,5,3};
        System.out.println(Arrays.toString(arr));
        HeapSort hs = new HeapSort();
        int maxheap[] = hs.createMaxHeap(arr);
        System.out.println(Arrays.toString(maxheap));
        hs.heapSort(maxheap);
        System.out.println(Arrays.toString(maxheap));
    }
}
