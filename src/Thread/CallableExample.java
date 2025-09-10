package Thread;

import java.util.Random;
import java.util.concurrent.*;

class RandomCallable implements Callable<Integer>{
    private int val;

    public RandomCallable(int val){
        this.val = val;
    }

    @Override
    public Integer call(){
        return new Random().nextInt() * val;
    }
}
public class CallableExample {

    public static void main(String[] args) {
        Future<Integer>[] futureArray = new Future[10];

        for(int i=0; i<futureArray.length; i++){
            RandomCallable randomCallable = new RandomCallable(i);
            FutureTask<Integer> fTask = new FutureTask<>(randomCallable);
            futureArray[i] = fTask;
            new Thread(fTask).start();
        }

        for(int i=0; i<futureArray.length; i++){
            try{
                Integer val= futureArray[i].get();
                System.out.println(val);
            }catch(InterruptedException ie){
            }catch (ExecutionException ee){
            }catch (CancellationException ce){}
        }
    }
}
