package graph.kruskals_algo;

import java.util.ArrayList;
import java.util.List;

//If edges is less then use kruskals algo  =>  O(E log V)
public class MinCostToConnectAllPoint {
    public static int kruskals_algo(int[][] vertices){
        List<int[]> edgesWithCost = populateEdgesWithCost(vertices);
        edgesWithCost.sort((a, b)-> a[2]-b[2]);
        //Set<Integer> visited = new HashSet<>();
        UnionFind uf = new UnionFind(vertices.length);
        int totalCost = 0;
        for(int[] edgeNcost : edgesWithCost){
            int vertex1 = edgeNcost[0];
            int vertex2 = edgeNcost[1];
            int cost = edgeNcost[2];
            /*if(!visited.contains(vertex1) || !visited.contains(vertex2)){
                visited.add(vertex1);
                visited.add(vertex2);
                totalCost += cost;
            }*/
            if(uf.union(vertex1, vertex2)){
                totalCost += cost;
            }
        }
        return totalCost;
    }

    private static List<int[]> populateEdgesWithCost(int[][] vertices) {
        List<int[]> result = new ArrayList<>();
        for(int i=0; i<vertices.length; i++){
            int x1 = vertices[i][0];
            int y1 = vertices[i][1];
            for(int j=i+1; j<vertices.length; j++){
                int x2 = vertices[j][0];
                int y2 = vertices[j][1];
                int cost = Math.abs(x1-x2) + Math.abs(y1-y2);
                result.add(new int[]{i, j, cost});
            }
        }
        return result;
    }
    static class UnionFind{
        private int[] parent,rank;
        public UnionFind(int n){
            parent = new int[n];
            rank = new int[n];
            for(int i=0; i<n; i++){
                parent[i] = i;
                rank[i] = 1;
            }
        }
        public int find(int n){
            if(n != parent[n]){
                parent[n] = find(parent[n]);
            }
            return parent[n];
        }
        public boolean union(int u, int v){
            int up = find(u);
            int vp = find(v);
            if(up == vp){
                return false;
            }
            if(rank[up] > rank[vp]){
                parent[vp] = up;
                rank[up] = rank[up] + rank[vp];
            }else{
                parent[up] = vp;
                rank[vp] = rank[vp] + rank[up];
            }
            return true;
        }
    }

    public static void main(String[] args) {
        System.out.println("expected 10 # "+kruskals_algo(new int[][] {{0,0},{2,2},{3,3},{2,4},{4,2}}));
    }
}
