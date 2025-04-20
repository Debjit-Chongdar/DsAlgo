package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data){
            this.data = data;
        }
    }

    public static Node insert(Node root, int data){
        if(root == null){
            return new Node(data);
        }
        if(root.data > data){
            root.left = insert(root.left, data);
        }else if(root.data < data){
            root.right = insert(root.right, data);
        }else{
            throw new IllegalArgumentException("same value already exist");
        }
        return root;
    }
    public static Node delete(Node root, int data){
        if(root == null){
            throw new IllegalArgumentException("No such element present");
        }
        if(root.data == data){
            if(root.left == null){ //if root has not left child
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            if(root.left.right == null){
                root.left.right = root.right;
                return root.left;
            }
            if(root.right.left == null){
                root.right.left = root.left;
                return root.right;
            }
            //replace by left side max element or by right side min element
            Node pre = root.left;
            Node node = pre.right;
            while(node.right != null){
                pre = node;
                node = node.right;
            }
            pre.right = node.left;
            node.left = root.left;
            node.right = root.right;
            return node;
        }else if(root.data < data){
            root.right = delete(root.right, data);
        }else{
            root.left = delete(root.left, data);
        }
        return root;
    }

    public static void main(String[] args) {
        int[] bstData = {5,7,2,4,8,9,1,3,6};
        Node root = testInsert(bstData);
        root = testDelete(root, 5);
        root = insert(root, 5);
        root = testDelete(root, 8);
        root = testDelete(root, 2);
    }
    static Node testInsert(int[] arr){
        Node root = insert(null, arr[0]);
        for(int i=1; i<arr.length; i++){
            root = insert(root, arr[i]);
        }
        showData(root);
        return root;
    }
    static Node testDelete(Node root, int data){
        root = delete(root, data);
        System.out.println("after delete "+data);
        showData(root);
        return root;
    }
    static void showData(Node root){
        printInOrder(root);
        System.out.println();
        printPreOrder(root);
        System.out.println();
        printBFS(root);
        System.out.println();
    }
    static void printInOrder(Node root){
        if(root == null){
            return;
        }
        printInOrder(root.left);
        System.out.print(root.data + " ");
        printInOrder(root.right);
    }
    static void printPreOrder(Node root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }
    static void printBFS(Node root){
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.data + " ");
            if(node.left != null) {
                queue.offer(node.left);
            }
            if(node.right != null){
                queue.offer(node.right);
            }
        }
    }
}
