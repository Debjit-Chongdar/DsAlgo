package Thread;

public class Example {
    private final Object lock = new Object();

    public void sleepTest() throws InterruptedException {
        System.out.println(" start sleep test");
        Thread.sleep(2000);
        System.out.println("End of sleep test");

        Thread t1 = new Thread(() -> System.out.println("Thread t1 running"));
        t1.start();
        t1.join(); // t1.join(1000) // max it will wait for 1000 milli second
        System.out.println(" Main thread continue");
    }

    public void InterruptedTest(){
        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        t1.start();
        System.out.println(t1.isAlive());
        t1.interrupt();
        System.out.println(t1.isAlive());
        //use case
        //while (!Thread.currentThread().isInterrupted()){
            // do the work
        //}
    }

    public void waitTest(){
        Thread t1 = new Thread(() -> {
            synchronized (lock){ // accure lock
                System.out.println(" thread1 wait");
                try {
                    lock.wait(); // release lock on lock object
                    System.out.println("thread1 resume");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (lock){
                System.out.println("thread 2 notifying thread 1");
                lock.notify();
            }
        });
        t1.start();
        t2.start();
    }
    public static void main(String[] args) throws InterruptedException {
        //new Example().sleepTest();
        //new Example().InterruptedTest();
        new Example().waitTest();
    }
}
