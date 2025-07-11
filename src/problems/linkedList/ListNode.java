package problems.linkedList;

public class ListNode {
    public int data;
    public ListNode next;
    public ListNode(int val){
        this.data = val;
        this.next = null;
    }
    public ListNode(int val, ListNode node){
        this.data = val;
        this.next = node;
    }
}
