package tree.problems;

import java.util.ArrayList;
import java.util.List;

class Node{
    int data;
    Node left;
    Node right;
    public Node(int data){
        this.data = data;
    }
}
public class TraversOneNodeToAnotherNode {

    public static List<Node> findTraversPath(Node root, Node start, Node target){
        List<Node> rootToStartPath = new ArrayList<>();
        List<Node> rootToTargetPath = new ArrayList<>();
        boolean isStartNodeReachable = traversRootToTargetNode(root, start, rootToStartPath);//to populate list
        boolean isTargetNodeReachable = traversRootToTargetNode(root, target, rootToTargetPath);//to populate list
        List<Node> resultPath = new ArrayList<>();
        //if not reachable return empty list
        if(!isTargetNodeReachable || !isStartNodeReachable){
            return resultPath;
        }
        //it's not necessary to travers till root to reach target node
        int lastCommonRoot = 0;
        while(rootToStartPath.size() > lastCommonRoot+1 && rootToTargetPath.size() > lastCommonRoot+1
                && rootToTargetPath.get(lastCommonRoot+1) == rootToStartPath.get(lastCommonRoot+1)){
            lastCommonRoot++;
        }
        //source to root-1 add in the result list
        for(int s = rootToStartPath.size()-1; s>lastCommonRoot; s--){ //don't use 0 in condition
            resultPath.add(rootToStartPath.get(s));
        }
        // add root next to target that's why t=1
        for(int t=lastCommonRoot; t<rootToTargetPath.size(); t++){ //don't use 0 in condition
            resultPath.add(rootToTargetPath.get(t));
        }
        return resultPath;
    }

    private static boolean traversRootToTargetNode(Node root, Node target, List<Node> nodes){
        if(root == null){
            return false;
        }
        if(root == target){
            nodes.add(root); // we need to print target node as well
            return true;
        }
        nodes.add(root);
        if(traversRootToTargetNode(root.left, target, nodes) || traversRootToTargetNode(root.right, target, nodes)){
            return true;
        }
        //in case of falls statement we need to remove
        nodes.remove(nodes.size()-1);
        return false;
    }
    public static void printPath(List<Node> path){
        for(Node n : path){
            System.out.print("->"+n.data);
        }
    }

    public static void main(String[] args) {
        Node root = new Node(4);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n5 = new Node(5);
        Node n6 = new Node(6);
        n5.right = n6;
        root.right = n5;
        n2.right = n3;
        n2.left = n1;
        root.left = n2;

        printPath(findTraversPath(root, n3, n6));
        System.out.println();
        printPath(findTraversPath(root, n3, n1));
    }
    //        4
    //      /   \
    //     2    5
    //    /\     \
    //   1  3     6
}
