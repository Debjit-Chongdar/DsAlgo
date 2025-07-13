package linkedList;

import static linkedList.Util.showData;

// 1->2->3->4->5->6  (4'th) =>  1->2->3->5->6
public class RemoveNthNodeFromEnd {

    public static void removeNthNode(ListNode head, int n){
        if(n == 1){  // in void method this scenario is not possible only data copy is possible
            ListNode tmp = head; // if it has only one node we cant make head to null
            while(tmp != null && tmp.next != null){
                tmp.data = tmp.next.data;
                if(tmp.next.next == null){
                    tmp.next = null;
                }
                tmp = tmp.next;
            }
            return;
        }
        ListNode tmp = head;
        while(tmp != null && n>2){
            tmp = tmp.next;
            n--;
        }
        if(n == 2 && tmp.next != null){
            tmp.next = tmp.next.next;
        }
    }
    // 1->2->3->4->5 (2nd node) = 1->2->3->5
    // 1->2->3->4->5 (remove 5th node)  = 2->3->4->5 (it's complicated)
    public static ListNode removeNthNodeFromEnd(ListNode head, int n){
        ListNode slowNode = head;
        ListNode fastNode = head;
        ListNode preFast = null; //this node use to manage complicated scenario
        while(fastNode != null && n > 0){
            preFast = fastNode; // this one is required to manage delete 1st node
            fastNode = fastNode.next;
            n--;
        }
        if(n==0 && fastNode != null){
            while (fastNode != null && fastNode.next != null){
                fastNode = fastNode.next;
                slowNode = slowNode.next;
            }
            slowNode.next = slowNode.next.next;
        }
        if(fastNode==null && preFast != null){ // this one is required to manage delete 1st node
            return head.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode input = new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        showData(input);
        removeNthNode(input, 4);
        System.out.println();
        System.out.println("remove 4'th Node");
        showData(input);
        System.out.println();
        System.out.println("remove 1'st node");
        removeNthNode(input, 1);
        showData(input);
        System.out.println();
        System.out.println("remove 2nd node");
        removeNthNode(input, 2);
        showData(input);
        System.out.println();
        System.out.println("remove last node");
        removeNthNode(input, 2);
        showData(input);
        System.out.println();
        System.out.println("remove 1st node from 1 node list");
        removeNthNode(input, 1);
        showData(input);
        System.out.println();
        System.out.println(" ------------------------------ ");
        ListNode in1 = new ListNode(1,new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        showData(in1);
        System.out.println();
        in1 = removeNthNodeFromEnd(in1, 2);
        System.out.println("remove 2nd last node");
        showData(in1);
        System.out.println();
        in1 = removeNthNodeFromEnd(in1, 1);
        System.out.println("remove last node");
        showData(in1);
        System.out.println();
        in1 = removeNthNodeFromEnd(in1, 3);
        System.out.println("remove 1st element");
        showData(in1);
    }
}
