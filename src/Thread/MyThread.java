package Thread;

class Thrd extends Thread{
    @Override
    public void run(){
        System.out.println("Thread run method call");
    }
}
class Runble implements Runnable{
    @Override
    public void run(){
        System.out.println("Runnable run method call");
    }
}

public class MyThread {
    public static void main(String[] args) {
        Thrd thread = new Thrd();
        thread.start();
        Runble runnable = new Runble();
        new Thread(runnable).start();
    }
}
