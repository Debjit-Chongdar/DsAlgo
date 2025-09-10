package Thread;

public class DeadLock {
    private final String s1 = "Test1";
    private final String s2 = "Test2";

    public void method_1 (){
        new Thread(){
            @Override
            public void run(){
                synchronized(s1){
                    System.out.println(s1+" from method_1");
                    try{
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                    synchronized (s2){
                        System.out.println(s2+" from method_1");
                    }
                }
            }
        }.start();
    }
    public void method_2 (){
        new Thread(){
            @Override
            public void run(){
                synchronized(s2){
                    System.out.println(s2+" from method_2");
                    try{
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                    }
                    synchronized (s1){
                        System.out.println(s1+" from method_2");
                    }
                }
            }
        }.start();
    }

    public static void main(String[] args) {
        DeadLock deadLock = new DeadLock();
        deadLock.method_1();
        deadLock.method_2();
    }
}
