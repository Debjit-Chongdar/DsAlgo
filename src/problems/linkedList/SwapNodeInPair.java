package problems.linkedList;

import static problems.linkedList.Util.showData;

//Swap node in pairs
// 1->2->3->4  Result = 2->1->4->3
public class SwapNodeInPair {
    public static ListNode swapInPair(ListNode head){
        if(head == null || head.next == null){
            return  head;
        }
        ListNode next = head.next; //next is 2nd node
        if(next != null){
            ListNode tmp = next.next; // keep 3rd node in tmp for further processing
            next.next = head;   // swapping
            head.next = swapInPair(tmp);    // first node next will be return val of 3rd node
        }
        return next; //return 2nd node
    }

    public static void main(String[] args) {
        ListNode input = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7)))))));
        showData(input);
        System.out.println();
        showData(swapInPair(input));
    }
}
