package problems;

public class WaterProblem {
    public static int trappingRainWater(int[] wall){
        int leftMax = 0;
        int rightMax = wall.length-1;
        int left = 0;
        int right = rightMax;
        int water = 0;
        while(leftMax<rightMax){
            if(wall[leftMax] < wall[rightMax]){
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
        int water = trappingRainWater(wall);
        System.out.println(water);
        int[] wall1 = {4,2,0,3,2,5};
        System.out.println(trappingRainWater(wall1));
        int[] wall2 = {4,2};
        System.out.println(trappingRainWater(wall2));
        int[] wall3 = {4,2,3};
        System.out.println(trappingRainWater(wall3));
        int[] wall0 = {4};
        System.out.println(trappingRainWater(wall0));

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
