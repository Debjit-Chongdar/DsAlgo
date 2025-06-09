package range_query;

public class RangeSum2DArray {
    //top left row, col is starting and bottom right row, col is end
    public static int sumRange_PrefixSum_1D(int[][] nums, int startRow, int startCol, int endRow, int endCol){
        int[][] prefixSum = new int[nums.length+1][nums[0].length+1];
        //populate prefixSum where 0'th row and 0'th column will have 0 value and will populate every next row,col
        for(int r=0; r<nums.length; r++){
            for(int c=0; c<nums[0].length; c++){
                prefixSum[r+1][c+1] = prefixSum[r+1][c] + nums[r][c];
            }
        }
        //to calculate values of all row fall between start and end
        int total = 0;
        for(int rw=startRow+1; rw <= endRow+1; rw++){
            //As we populated next row col, so we have to fetch from next row col
            total = total + prefixSum[rw][endCol+1] - prefixSum[rw][startCol];
        }
        return total;
    }

    public static int sumRange_PrefixSum_2D(int[][] nums, int startRow, int startCol, int endRow, int endCol) {
        int[][] prefixSum = new int[nums.length + 1][nums[0].length + 1];
        //populate prefixSum and 0'th row and 0'th column will have 0 value
        for(int r=0; r<nums.length; r++){
            int prefix=0;
            for(int c=0; c<nums[0].length; c++){
                //add previous+current+above
                prefix+=nums[r][c];
                int above = prefixSum[r][c+1];
                prefixSum[r+1][c+1] = prefix + above;
                //below code will not work as prefixSum[r+1][c] has previousValue+aboveValue+it's own value
                //where as we don't need previous above val into accout as we will take current above val.
                //prefixSum[r+1][c+1] = prefixSum[r+1][c] + nums[r][c] + prefixSum[r][c+1];
             }
        }
        // fetch the total of given area/row,col
        //val(end#row,end#col) - val(start#row-1,end#col) - val(end#row,start#col-1) + val(start#row-1,start#col-1) => as this part is substracted two times
        int total = prefixSum[endRow+1][endCol+1] - prefixSum[startRow][endCol+1] - prefixSum[endRow+1][startCol] + prefixSum[startRow][startCol];
        return total;
    }
    public static void main(String[] args) {
        int[][] nums = {{3,0,1,4,2},
                        {5,6,3,2,1},
                        {1,2,0,1,5},
                        {4,1,0,1,7},
                        {1,0,3,0,5}};
        System.out.println("Expected output : 8 # Actual : "+sumRange_PrefixSum_1D(nums, 2,1,4,3));
        System.out.println("Expected output : 11 # Actual : "+sumRange_PrefixSum_1D(nums, 1,1,2,2));
        System.out.println("Expected output : 12 # Actual : "+sumRange_PrefixSum_1D(nums, 1,2,2,4));
        System.out.println("---------------------- 2D output ------------------------------");
        System.out.println("Expected output : 8 # Actual : "+sumRange_PrefixSum_2D(nums, 2,1,4,3));
        System.out.println("Expected output : 11 # Actual : "+sumRange_PrefixSum_2D(nums, 1,1,2,2));
        System.out.println("Expected output : 12 # Actual : "+sumRange_PrefixSum_2D(nums, 1,2,2,4));
    }
}
