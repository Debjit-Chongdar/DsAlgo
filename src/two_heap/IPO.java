package two_heap;

import java.util.PriorityQueue;

public class IPO {

    public int findMaximizeCapital(int[] capital, int[] profit, int startCap, int window){
        //int[] int[0]=>capital, int[1] => profit
        PriorityQueue<int[]> smallCapitalWithProfit = new PriorityQueue<>((a,b) -> a[0]-b[0]); //it will be ascending order
        PriorityQueue<Integer> maxProfit = new PriorityQueue<>((a,b) -> b-a); // it needs to be descending order
        //add all the capital profit in smallCapitalQueue
        for(int i=0; i<capital.length; i++){
            smallCapitalWithProfit.offer(new int[]{capital[i], profit[i]});
        }
        //calculate capital
        for(int w=0; w<window; w++){
            while(!smallCapitalWithProfit.isEmpty() && smallCapitalWithProfit.peek()[0] <= startCap){
                maxProfit.offer(smallCapitalWithProfit.poll()[1]);
            }
            if(!maxProfit.isEmpty()){
                startCap +=maxProfit.poll();
            }
        }
        return startCap;
    }

    public static void main(String[] args) {
        System.out.println("Expected = 8 & actual = "+ new IPO().findMaximizeCapital(new int[]{0,3,1,1}, new int[]{1,4,2,3}, 0, 3));
        System.out.println("Expected = 14 & actual = "+ new IPO().findMaximizeCapital(new int[]{4,4,2,3,3}, new int[]{2,3,1,5,3}, 2, 4));
    }
}
