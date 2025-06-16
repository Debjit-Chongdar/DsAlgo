package graph.union_find;

import java.util.Arrays;

public class FirstEdgeCreateCycle {

    public static int[] getEdgeCreateCycle(int[][] edges){
        int[] parent = new int[edges.length+1];
        int[] rank = new int[edges.length+1];
        for(int i=0; i<parent.length; i++){
            parent[i] = i;
        }
        Arrays.fill(rank, 1);
        for(int[] edge : edges){
            int u = edge[0];
            int v = edge[1];
            if(!unionFind(parent, rank, u, v)){
                return edge;
            }
        }
        return new int[0];
    }
    private static boolean unionFind(int[] parent, int[] rank, int u, int v) {
        int uParent = find(parent, u);
        int vParent = find(parent, v);
        if(uParent == vParent){
            return false;
        }
        if(rank[vParent] > rank[uParent]){
            parent[uParent] = parent[vParent];
            rank[vParent] = rank[vParent] + rank[uParent];
        }else{
            parent[vParent] = parent[uParent];
            rank[uParent] = rank[uParent] + rank[vParent];
        }
        return true;
    }
    private static int find(int[] parent, int n){
        if(parent[n] != n){
            return find(parent, parent[n]);
        }
        return parent[n];
    }
    //this is optimized unionFind
    private static boolean unionFindOptimized(int[] parent, int[] rank, int u, int v) {
        int uParent = parent[u]; //find parent of u
        int vParent = parent[v]; //find parent of v
        if(uParent == vParent){
            return false;
        }
        if(rank[vParent] > rank[uParent]){
            parent[u] = vParent;
            rank[vParent] = rank[vParent] + rank[uParent];
        }else{
            parent[v] = uParent;
            rank[uParent] = rank[uParent] + rank[vParent];
        }
        return true;
    }

    public static void main(String[] args) {
        int[][] input1 = {{1,2},{1,3},{1,4},{3,4},{3,5}};
        int[][] input2 = {{1,2},{2,3},{3,4},{4,5},{1,4}};
        System.out.println("expected = [3,4] # Actual = "+Arrays.toString(getEdgeCreateCycle(input1)));
        System.out.println("expected = [1,4] # Actual = "+Arrays.toString(getEdgeCreateCycle(input2)));
    }
}
