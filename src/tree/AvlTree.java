package tree;


public class AvlTree {
    static class Node{
        int val;
        Node left;
        Node right;
        int height;
        public Node(int val){
            this.val = val;
            this.height = 1;
        }
    }
    private int height(Node node){
        if(node == null){
            return 0;
        }
        return node.height;
    }
    private int balance(Node node){
        return height(node.left)-height(node.right);
    }
    private Node llRotation(Node root){
        Node node = root.left;
        root.left = node.right;
        node.right = root;
        root.height = 1 + Math.max(height(root.left), height(root.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }
    private Node rrRotation(Node root){
        Node node = root.right;
        root.right = node.left;
        node.left = root;
        root.height = 1 + Math.max(height(root.left), height(root.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }
    private Node lrRotation(Node root){
        Node node = root.left.right;
        Node tmp = root.left;
        root.left.right = node.left;
        root.left = node.right;
        tmp.height = 1 + Math.max(height(tmp.left), height(tmp.right));
        root.height = 1 + Math.max(height(root.left), height(root.right));
        node.left = tmp;
        node.right = root;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }
    private Node rlRotation(Node root){
        Node node = root.right.left;
        Node tmp = root.right;
        root.right = tmp.left.left;
        tmp.left = tmp.left.right;
        tmp.height = 1 + Math.max(height(tmp.left), height(tmp.right));
        root.height = 1 + Math.max(height(root.left), height(root.right));
        node.left = root;
        node.right = tmp;
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }
    public Node insert(Node root, int val){
        if(root == null){
            return new Node(val);
        }
        if(val < root.val){
            root.left = insert(root.left, val);
        }
        if(val > root.val){
            root.right = insert(root.right, val);
        }
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balance = balance(root);
        if(balance>1 && val<root.left.val){
            return llRotation(root);
        }else if(balance >1 && val > root.left.val){
            return lrRotation(root);
        }else if(balance<-1 && val>root.right.val){
            return rrRotation(root);
        }else if (balance<-1 && val < root.right.val){
            return rlRotation(root);
        }else {
            return root;
        }
    }

    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        Node root = avlTree.insert(null,3);
        int[] arr = {2,6,7,4,1,8,9,10,5,3};
        for(int i : arr){
            root = avlTree.insert(root,i);
        }
        show(root);
    }

    private static void show(Node root) {
        if(root == null){
            return;
        }
        show(root.left);
        System.out.println(root.val);
        show(root.right);
    }
}
