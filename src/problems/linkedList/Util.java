package problems.linkedList;

public class Util {
    public static void showData(ListNode node){
        while (node != null){
            System.out.print("->"+node.data);
            node = node.next;
        }
    }
}
