package problems.two_pointer;

public class WaterProblem {
    //Simplified version
    public static int trappingRainWaterSimple(int[] wall){
        int left = 0;
        int leftMaxHeight = wall[left];
        int right = wall.length-1;
        int rightMaxHeight = wall[right];
        int totalWater = 0;
        while (left < right){
            if(leftMaxHeight < rightMaxHeight){
                left++; // increment should be first
                leftMaxHeight = Math.max(leftMaxHeight, wall[left]);
                totalWater += leftMaxHeight - wall[left];
            }else{
                right--; // decrement should be first
                rightMaxHeight = Math.max(rightMaxHeight, wall[right]);
                totalWater += rightMaxHeight - wall[right];
            }
        }
        return totalWater;
    }

    public static int trappingRainWater(int[] wall){
        int leftMax = 0;
        int rightMax = wall.length-1;
        int left = 0;
        int right = rightMax;
        int water = 0;
        while(left < right){
            if(wall[left] < wall[right]){
                if(wall[leftMax] > wall[left]){
                    water = water + (wall[leftMax] - wall[left]);
                }else{
                    leftMax = left;
                }
                left = left + 1;
            }else{
                if(wall[rightMax] > wall[right]){
                    water = water + (wall[rightMax]-wall[right]);
                }else{
                    rightMax = right;
                }
                right = right-1;
            }
        }
        return water;
    }
    public static int containerWithMostWater(int[] height){
        int left = 0;
        int right = height.length-1;
        int area = 0;
        while(left < right){
            int tmpArea;
            if(height[left] > height[right]){
                tmpArea = (right - left) * height[right];
                right--;
            }else{
                tmpArea = (right - left) * height[left];
                left++;
            }
            area = Math.max(area, tmpArea);
        }
        return area;
    }

    public static void main(String[] args) {
        int[] wall = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println(trappingRainWater(wall) +" == "+trappingRainWaterSimple(wall));
        System.out.println(trappingRainWater(new int[]{4,2,0,3,2,5})+" == "+trappingRainWaterSimple(new int[]{4,2,0,3,2,5}));
        System.out.println(trappingRainWater(new int[]{4,2})+" == "+trappingRainWaterSimple(new int[]{4,2}));
        System.out.println(trappingRainWater(new int[]{4,2,3})+" == "+trappingRainWaterSimple(new int[]{4,2,3}));

        int[] height = {1,8,6,2,5,4,8,3,7};
        System.out.println(containerWithMostWater(height)+" == 49");
        int[] height1 = {3};
        System.out.println(containerWithMostWater(height1)+" == 0");
        int[] height2 = {4,8};
        System.out.println(containerWithMostWater(height2)+" == 4");
        int[] height3 = {1,3,1};
        System.out.println(containerWithMostWater(height3)+" == 2");
    }
}
