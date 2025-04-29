package design_pattern.object_creation;

public class Singleton {
    //Egar initilization
    private static Singleton instanceEager = new Singleton();
    private static Singleton instanceLazy = null;

    private Singleton(){
    }

    public static Singleton getInstanceEager(){
        return instanceEager;
    }
    //Lazy initialize syncronized
    public static synchronized Singleton getSyncInstance(){
        if(instanceLazy == null){
            instanceLazy = new Singleton();
        }
        return instanceLazy;
    }
    // Double Checked Locking based Java implementation of
    public static Singleton getDSyncInstance(){
        if (instanceLazy == null){
            synchronized (Singleton.class){
                if(instanceLazy == null){
                    instanceLazy = new Singleton();
                }
            }
        }
        return instanceLazy;
    }

    public static void main(String[] args) {
        Singleton s1 = getInstanceEager();
        System.out.println(s1.hashCode());
        Singleton s2 = getInstanceEager();
        System.out.println(s2.hashCode());
        Singleton s3 = getSyncInstance();
        System.out.println(s3.hashCode());
        Singleton s4 = getSyncInstance();
        System.out.println(s4.hashCode());
        Singleton s5 = getDSyncInstance();
        System.out.println(s5.hashCode());
        Singleton s6 = getDSyncInstance();
        System.out.println(s6.hashCode());
    }
}
