package problems.linkedList;

import static problems.linkedList.Util.showData;

// 1->2->3->4   result= 4->3->2->1
public class ReverseLinkedList {
    public static ListNode reverse(ListNode head){
        if(head == null){
            return head;
        }
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

    public static ListNode reverseRecursion (ListNode head){
        if(head == null || head.next == null){
            return head;
        }
        ListNode node = reverseRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public static void main(String[] args) {
        ListNode input = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7, new ListNode(8))))))));
        showData(input);
        System.out.println();
        showData(reverse(input));
        System.out.println();
        ListNode input1 = new ListNode(6, new ListNode(7, new ListNode(8)));
        showData(input1);
        System.out.println();
        showData(reverseRecursion(input1));
    }
}
