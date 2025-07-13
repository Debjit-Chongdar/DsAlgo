package linkedList;

//1->2->3->7->8->9
//4->7->8->9            result => 7->8->9
public class IntersectNodes {
    public static ListNode intersectPoint(ListNode head1, ListNode head2){
        ListNode n1 = head1;
        ListNode n2 = head2;
        while(n1 != n2){
            n1 = (n1 == null) ? head2 : n1.next;
            n2 = (n2 == null) ? head1 : n2.next;
        }
        return n1;
    }

    public static void main(String[] args) {
        ListNode intersect = new ListNode(7, new ListNode(8, new ListNode(9)));
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, intersect)));
        ListNode l2 = new ListNode(4, intersect);
        ListNode result = intersectPoint(l1, l2);
        System.out.println(result.data);
    }
}
