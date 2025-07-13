package sliding_window.fixed_length;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaximumNumberEachWindow {

    //[1,2,1,0,4,2,6] window = 3, Result = [2, 2, 4, 4, 6]
    public static int[] maxNumberInWindow(int[] nums, int window){
        // first calculate the size of the target/result array
        int size = 0;
        if(nums.length == 0){
            return nums;
        }else if(nums.length<=window){
            size = 1;
        }else{
            size = 1 + nums.length - window;
        }
        int[] result = new int[size];
        //Create Priority queue with descending order
        //Create heap and sort heap takes nlogN time
        PriorityQueue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>(){
            @Override
            public int compare(Integer in1, Integer in2){
                return (in2 - in1);
            }
        });
        //add first elements till window-1
        for(int i=0; i<window-1; i++){
            queue.offer(nums[i]);
        }
        //add + peek + remove in sequence
        for(int i=window-1; i<nums.length; i++){
            queue.offer(nums[i]);
            result[(i+1)-window] = queue.peek(); //don't use poll here then we have to add again
            queue.remove(nums[(i+1)-window]);
        }
        return result;
    }
    public int[] maxNumberInWindow_segmentTree(int[] nums, int window){
        // first calculate the size of the target/result array
        int size = 0;
        if(nums.length == 0){
            return nums;
        }else if(nums.length<=window){
            size = 1;
        }else{
            size = 1 + nums.length - window;
        }
        int[] result = new int[size];
        //use segment tree to get result in each window
        SegTree segmentTree = new SegTree(nums.length);
        segmentTree.populateData(nums, 0, 0, nums.length-1);
        for(int i=0; i <= nums.length-window; i++){
            result[i] = segmentTree.getMaxValFromGivenRange(0, i, i+window-1, 0, nums.length-1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] result = maxNumberInWindow(new int[]{1,2,1,0,4,2,6}, 3);
        System.out.println(Arrays.toString(result));
        int[] result_seg = new MaximumNumberEachWindow().maxNumberInWindow_segmentTree(new int[]{1,2,1,0,4,2,6}, 3);
        System.out.println(Arrays.toString(result_seg));
    }

    class SegTree{
        private int[] tree;
        public SegTree(int size){
            int log2 = (int)Math.ceil(Math.log(size)/Math.log(2));
            int pow2 = (int)Math.pow(2, log2);
            tree = new int[(pow2 * 2)-1];
        }
        public void populateData(int[] data, int index, int left, int right){
            if(left == right){
                tree[index] = data[left];
                return;
            }
            int mid = (left+right)/2;
            populateData(data, (index*2)+1, left, mid);
            populateData(data, (index*2)+2, mid+1, right);
            tree[index] = Math.max(tree[(index*2)+1], tree[(index*2)+2]);
        }
        public int getMaxValFromGivenRange(int index, int leftRange, int rightRange, int left, int right){
            if(leftRange<=left && right<=rightRange){
                return tree[index];
            }
            int mid = (left+right)/2;
            int max = 0;
            if(leftRange<=mid){
                max = Math.max(max, getMaxValFromGivenRange((index*2)+1, leftRange, rightRange, left, mid));
            }
            if(rightRange > mid){
                max = Math.max(max, getMaxValFromGivenRange((index*2)+2, leftRange,rightRange, mid+1, right));
            }
            return max;
        }
    }
}
