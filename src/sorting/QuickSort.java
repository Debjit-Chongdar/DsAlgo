package sorting;

import java.util.Arrays;

import static sorting.Util.swap;

public class QuickSort {

    public void sort(int[] arr){
        sort(arr, 0, arr.length-1);
    }
    private void sort(int[] arr, int start, int end){
        int pivot = pivotPosition(arr, start, end);
     //   int pivot = hoaresPartition(arr, start, end);
        if(pivot > start+1) {
            sort(arr, start, pivot-1);
        }
        if(pivot+1 < end) {
            sort(arr, pivot + 1, end);
        }
    }
    //Lomuto Partition
    private int pivotPosition(int[] arr, int start, int end){
        int pivotPosition = end;
        int startIndex = start;
        for(int i=startIndex; i<pivotPosition; i++){
            if(arr[i] < arr[pivotPosition]){
                swap(arr, startIndex, i);
                startIndex++;
            }
        }
        swap(arr, startIndex, pivotPosition);
        return startIndex;
    }
    //by creating differentArray
    private int naivePartition(int[] arr, int start, int end){
        return 0;
    }
    //check from both direction
    private int hoaresPartition(int[] arr, int start, int end){
        int pivotIndex = end;
        int s = start;
        int e = pivotIndex-1;
        //in case of two element came in req {1,2}
        if(s==e && arr[s] < arr[pivotIndex]){
            return pivotIndex;
        }
        while(s < e){
            while(s <= e && arr[s] < arr[pivotIndex]){
                s++;
            }
            while(e >= s && arr[e]>arr[pivotIndex]){
                e--;
            }
            if(s<e){
                swap(arr, s, e);
            }
        }
        swap(arr, s, pivotIndex);
        return s;
    }

    public static void main(String[] args) {
        int[] arr = {2,8,5,3,1,7,4,3};
        QuickSort quickSort = new QuickSort();
        quickSort.sort(arr);
        System.out.println(Arrays.toString(arr));
        int[] arr1 = {1,3,5,7};
        quickSort.sort(arr1);
        System.out.println(Arrays.toString(arr1));
    }
}
