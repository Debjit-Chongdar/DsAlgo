package graph.union_find;

//Union Find and Disjoint-Set are same just different name
public class UnionFind {
    private int[] parent;
    private int[] rank;

    public UnionFind(int length){   //if length =4
        parent = new int[length ]; //[0,1,2,3]
        rank = new int[length ];    // [1,1,1,1]
        for(int i=0; i<length; i++){
            parent[i] = i;
            rank[i] = 1;
        }
    }

    public boolean union(int u, int v){
        int uParent = find(u); // find ultimate parent of u
        int vParent = find(v);
        if(uParent == vParent){
            return false;
        }
        if(rank[vParent] > rank[uParent]){ // if rank of vParent is greater than rank of uParent
            parent[uParent] = vParent;      // then attach uParent under vParent
            rank[vParent] = rank[vParent] + rank[uParent]; // increase the rank by attached parent under it
        }else{
            parent[vParent] = uParent;
            rank[uParent] = rank[uParent] + rank[vParent];
        }
        return true;
    }
    // find the top parent for current node
    public int find(int n){
        if(n != parent[n]){ // if node has parent then find and populate top most parent to it's parent
            parent[n] = find(parent[n]);
        }
        return parent[n];
    }
}
