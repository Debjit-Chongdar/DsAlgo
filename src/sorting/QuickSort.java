package sorting;

import java.util.Arrays;

import static sorting.Util.swap;

public class QuickSort {

    public void sort(int[] arr){
        sort(arr, 0, arr.length-1);
    }
    private void sort(int[] arr, int start, int end){
      //  int pivot = pivotPosition(arr, start, end);
     //   int pivot = hoaresPartition(arr, start, end);
        int pivot = naivePartition(arr, start, end);
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
        //check start to end-1 position each element
        for(int i=startIndex; i<pivotPosition; i++){
            if(arr[i] < arr[pivotPosition]){
                swap(arr, startIndex, i); //to bring all lesser value to pivot's left side
                startIndex++;
            }
        }
        //now start index reached to next index of last lesser value index
        swap(arr, startIndex, pivotPosition);
        return startIndex;
    }
    //by creating differentArray
    private int naivePartition(int[] arr, int start, int end){
        int pivotVal = arr[end];
        //create a sub array by start to end
        int tmp[] = new int[(end-start)+1];
        //populate all lesser value than pivot to the new array
        int index =0;
        for(int i=start; i<end; i++){
            if(arr[i]<=pivotVal){
                tmp[index++]=arr[i];
            }
        }
        //populate pivot value and store the index to return
        int partitionIndex = start+index;
        tmp[index++] = pivotVal;
        //populate all bigger value to the array
        for(int i=start; i<end; i++){
            if(arr[i]>pivotVal){
                tmp[index++] = arr[i];
            }
        }
        //copy all element from tmp to arr(original)
        index = 0;
        for(int i=start; i<=end; i++){
            arr[i] = tmp[index++];
        }
        return partitionIndex;
    }
    //check from both direction
    private int hoaresPartition(int[] arr, int start, int end){
        int pivotIndex = end;
        int s = start;
        int e = pivotIndex-1;
        //in case of two element came in ascending order {3,7} where 7 is pivot val
        if(s==e && arr[s] < arr[pivotIndex]){
            return pivotIndex;
        }
        //for descending order val it will swap by last swap
        while(s < e){ //if use <= it will go to infinity loop
            //if e position element is lesser than pivot val then pivot should lye on e+1 position
            //don't allow to go beyond s & e
            while(s <= e && arr[s] < arr[pivotIndex]){
                s++;
            }
            while(e >= s && arr[e]>arr[pivotIndex]){
                e--;
            }
            //if s and e point to same index or cross each other, then don't swap
            if(s<e){
                swap(arr, s, e);
            }
        }
        //to populate pivot value into pivot index
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
