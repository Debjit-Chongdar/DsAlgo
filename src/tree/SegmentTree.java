package tree;

public class SegmentTree {
    int[] segmentTree;
    public void populateSegmentTree(int[] input){
        int log2 = (int)Math.ceil(Math.log(input.length)/Math.log(2));
        int length = ((int)Math.pow(2, log2) * 2)-1 ;
        segmentTree = new int[length];
        populateTree(input, 0, 0, input.length-1);
    }
    private void populateTree(int[] arr, int index, int left, int right){
        if(left == right){
            segmentTree[index] = arr[left];
            return;
        }
        int mid = (left+right)/2;
        populateTree(arr, index*2+1, left, mid);
        populateTree(arr, index*2+2, mid+1, right);
        segmentTree[index] = segmentTree[index*2+1] + segmentTree[index*2+2];
    }
    private int sumOfARange(int leftRange, int rightRange, int left, int right, int index){
        if(leftRange<=left && right<=rightRange){
            return segmentTree[index];
        }
        int mid = (left + right)/2;
        int sum = 0;
        if(leftRange <= mid){
            sum += sumOfARange(leftRange, rightRange, left, mid, index*2+1);
        }
        if(rightRange > mid){
            sum += sumOfARange(leftRange, rightRange, mid+1, right, index*2+2);
        }
        return sum;
    }
    public int sumOfARange(int[] input, int leftRange, int rightRange){
        return sumOfARange(leftRange, rightRange, 0, input.length-1, 0);
    }

    public static void main(String[] args) {
        int[] input = {2,6,7,3,4,1,5};
        SegmentTree sg = new SegmentTree();
        sg.populateSegmentTree(input);
        System.out.println("expecting sum = 20 : "+ sg.sumOfARange(input,1, 4));
        System.out.println("expecting sum = 6 : "+ sg.sumOfARange(input,5, 6));
        System.out.println("expecting sum = 2 : "+ sg.sumOfARange(input,0, 0));

    }
}
