package problems.linkedList;

import static problems.linkedList.Util.showData;

public class SwapNodeInPair {
    public static ListNode swapInPair(ListNode head){
        if(head == null || head.next == null){
            return  head;
        }
        ListNode next = head.next;
        if(next != null){
            ListNode tmp = next.next;
            next.next = head;
            head.next = swapInPair(tmp);
        }
        return next;
    }

    public static void main(String[] args) {
        ListNode input = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7)))))));
        showData(input);
        System.out.println();
        showData(swapInPair(input));
    }
}
