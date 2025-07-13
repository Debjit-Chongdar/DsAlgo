package linkedList;

//1->2->3->4->5->3....
public class LoopPresentInLinkedList {
    public static boolean isLoopPresent(ListNode head){
        if(head == null){
            return false;
        }
        ListNode slowNode = head;
        ListNode fastNode = head;
        while(fastNode != null && fastNode.next != null){
            fastNode = fastNode.next.next;
            if(slowNode == fastNode){
                return true;
            }
            slowNode = slowNode.next;
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node3 = new ListNode(3, new ListNode(4, node5));
        ListNode node = new ListNode(1, new ListNode(2, node3));
        node5.next = node3;
        System.out.println(isLoopPresent(node));
        ListNode node1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println(isLoopPresent(node1));
    }
}
