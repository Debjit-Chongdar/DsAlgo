package monotonic_stack;

import java.util.Arrays;
import java.util.Stack;

public class NextWarmerDay {

    //[73,74,75,71,69,72,76,73] => [1,1,4,2,1,1,-1,-1]
    public static int[] waitingIntervalForWarmerDays(int[] temps){
        int[] result = new int[temps.length];
        Arrays.fill(result, -1);
        Stack<Integer> decresingMonotonicStack = new Stack<>();
        for(int i=0; i<temps.length; i++){
           while (!decresingMonotonicStack.isEmpty() && temps[decresingMonotonicStack.peek()] < temps[i]){
               int index = decresingMonotonicStack.pop();
               result[index] = i-index;
           }
           decresingMonotonicStack.push(i);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(waitingIntervalForWarmerDays(new int[]{73,74,75,71,69,72,76,73})));
    }
}
