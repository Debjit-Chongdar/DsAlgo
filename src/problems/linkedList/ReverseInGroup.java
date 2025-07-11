package problems.linkedList;

import java.util.LinkedList;

import static problems.linkedList.Util.showData;

//1->2->3->4->5->6->7->8 group = 3
// result = 3->2->1  ->6->5->4  ->8->7
public class ReverseInGroup {
    public static ListNode reverseByGroup(ListNode head, int group){
        if(head == null){
            return head;
        }
        ListNode curNode = head;
        ListNode preNode = null;
        int tmpGrp = group;
        while(tmpGrp>0 && curNode != null){
            ListNode tmp = curNode.next;
            curNode.next = preNode;
            preNode = curNode;
            curNode = tmp;
            tmpGrp--;
        }
        head.next = reverseByGroup(curNode, group);
        return preNode;
    }

    public static void main(String[] args) {
        ListNode input = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, new ListNode(7, new ListNode(8))))))));
        showData(input);
        ListNode output = reverseByGroup(input, 3);
        System.out.println();
        showData(output);
    }
}
