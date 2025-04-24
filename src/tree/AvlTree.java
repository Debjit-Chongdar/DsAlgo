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
        if(node == null){
            return 0;
        }
        return height(node.left)-height(node.right);
    }
    private Node llRotation(Node root){
        Node node = root.left;
        root.left = node.right;
        node.right = root;
        // root and node changing the position so we have to recalculate height for them
        root.height = 1 + Math.max(height(root.left), height(root.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }
    private Node rrRotation(Node root){
        Node node = root.right;
        root.right = node.left;
        node.left = root;
        //root and node changing the position so we have to recalculate height for them
        root.height = 1 + Math.max(height(root.left), height(root.right));
        node.height = 1 + Math.max(height(node.left), height(node.right));
        return node;
    }
    private Node lrRotation(Node root){
        Node node = root.left.right;
        Node tmp = root.left;
        root.left.right = node.left;
        root.left = node.right;
        //tmp, root and node changing the position so we have to recalculate height for them
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
        //tmp, root and node changing the position so we have to recalculate height for them
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
        //recalculate height and rotate to balance
        //rotate based on val and balance
        root.height = Math.max(height(root.left), height(root.right))+1;

        if(balance(root) > 1 && val < root.left.val){
            return llRotation(root);
        }
        if(balance(root) > 1 && val > root.left.val){
            return lrRotation(root);
        }
        if(balance(root) < -1 && val > root.right.val){
            return rrRotation(root);
        }
        if(balance(root) < -1 && val < root.right.val){
            return rlRotation(root);
        }
        return root;
    }

    public Node delete(Node root, int val){
        if(root == null){
            throw new IllegalArgumentException("No matching found");
        }
        if(val > root.val){
            root.right = delete(root.right, val);
        }
        if(val < root.val){
            root.left = delete(root.left, val);
        }
        if(val == root.val){ //this root need to be deleted
            if(root.left == null){
                return root.right;
            }
            if(root.right == null){
                return root.left;
            }
            //copy the max val from left subtree or min val from right subtree to root
            //delete max val node from left subtree or min val node from right subtree
            Node maxChild = root.left;
            while(maxChild.right != null){
                maxChild = maxChild.right;
            }
            root.val = maxChild.val;
            root.left = delete(root.left, maxChild.val);
            /*OR
            Node rMinChild = root.right;
            while(rMinChild.left != null){
                rMinChild = rMinChild.left;
            }
            root.val = rMinChild.val;
            root.right = delete(root.right, rMinChild.val);*/
        }
        //recalculate height and rotate to balance
        //rotate based on right balance & left balance
        root.height = Math.max(height(root.left), height(root.right))+1;
        if(balance(root) > 1 && balance(root.left) >= 0){
            return llRotation(root);
        }
        if(balance(root) > 1 && balance(root.left) < 0){
            return lrRotation(root);
        }
        if(balance(root) < -1 && balance(root.right) <= 0){
            return rrRotation(root);
        }
        if(balance(root) < -1 && balance(root.right) > 0){
            return rlRotation(root);
        }
        return root;
    }

    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        Node root = avlTree.insert(null,3);
        int[] arr = {7,2,13,6,15,4,11,1,10,8,15,9,10,5,14};
        for(int i : arr){
            root = avlTree.insert(root,i);
        }
        show(root);
        root = avlTree.delete(root, 7);
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
