package problems;

import java.util.ArrayList;
import java.util.List;

class Tree{
    int start;
    int end;
    Tree left;
    Tree right;
    public Tree(int start, int end){
        this.start = start;
        this.end = end;
    }
}

public class MyCalender_BST {
    private List<int[]> meetings;
    public MyCalender_BST(){
        meetings = new ArrayList<>();
    }
    //add a new meeting, it should not conflict with any other meeting
    public boolean createMeeting(int start, int end){
        for(int[] meeting : meetings){
            //if((start>=meeting[0] && start<meeting[1]) || (end>meeting[0] && end<=meeting[1])){
            if(start < meeting[1] && end > meeting[0]){ //important statement to understand
                return false;
            }
        }
        meetings.add(new int[]{start, end});
        return true;
    }
    //using BST
    private Tree root;
    public boolean createMeeting_BST(int start, int end){
        Tree node = new Tree(start, end);
        if(root == null){
            root = node;
            return true;
        }
        Tree cur = root;
        while(true){
            if(end <= cur.start){ // check insert position is on left side of root
                if(cur.left == null){
                    cur.left = node;
                    return true;
                }
                cur = cur.left;
            }else if(start >= cur.end){ // check insert position is on right side of root
                if(cur.right == null){
                    cur.right = node;
                    return true;
                }
                cur = cur.right;
            }else{      // insert position within/overlapping existing scope
                return false;
            }
        }
    }
    public static void main(String[] args) {
        MyCalender_BST myCalender = new MyCalender_BST();
        int[][] input = {{10,20},{15,25},{20,30}};
        for (int[] in:input){
            System.out.println(myCalender.createMeeting(in[0], in[1]));
            System.out.println(myCalender.createMeeting_BST(in[0], in[1]));
        }
    }
}
