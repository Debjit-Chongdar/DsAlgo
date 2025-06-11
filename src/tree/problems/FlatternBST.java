package tree.problems;

import java.util.Stack;

public class FlatternBST {
    static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
        }
    }

    public Node flatternPreOrder(Node root){
        Stack<Node> stack = new Stack<>();
        Node tmp = root;
        while(tmp != null || !stack.isEmpty()){
            if(tmp == null){
                tmp = stack.pop();
            }
            if(tmp.right != null){
                stack.push(tmp.right);
            }
            if(tmp.left != null) {
                tmp.right = tmp.left;
                tmp.left = null;
            }else if(!stack.isEmpty()) {
                tmp.right = stack.pop();
            }else{
                tmp.right = null;
            }
            tmp = tmp.right;
        }
        return root;
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(5);
        root.left.left = new Node(3);
        root.left.right = new Node(4);
        root.right.right = new Node(6);
        Node result = new FlatternBST().flatternPreOrder(root);
        while (result!=null){
            System.out.println(result.data);
            result = result.right;
        }
    }
}
