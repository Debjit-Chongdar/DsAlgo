package linkedList;

import static linkedList.Util.showData;

// 1->3->4->6
// 0->2->5->7->8
public class MergeTwoSortedList {

    public static ListNode merge(ListNode head1, ListNode head2){
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        ListNode tmp = head1;
        if(head2.data < tmp.data){ // if first data of head2 is lesser than head1
            tmp = head2;
            head2 = head1;
            head1 = tmp; //as we are ruturning head1 so it need to be populated
        }
        while(head2 != null && tmp.next != null){ // until head2 is null, tmp can't be null
            if(tmp.next.data<head2.data){
                tmp = tmp.next;
            }else{
                ListNode tmpNext = tmp.next;
                ListNode head2Next = head2.next;
                tmp.next=head2;
                head2.next = tmpNext;
                head2 = head2Next;
                tmp = tmp.next;
            }
        }
        if(tmp != null && head2 != null){ //if head2 left off then attach next to tmp
            tmp.next = head2;
        }
        return head1;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1,new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(7)))));
        ListNode node2 = new ListNode(0, new ListNode(2, new ListNode(6, new ListNode(8))));
        ListNode result = merge(node1, node2);
        showData(result);
    }
}
