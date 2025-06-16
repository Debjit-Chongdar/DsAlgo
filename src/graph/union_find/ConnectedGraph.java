package graph.union_find;

public class ConnectedGraph {
    //how many connected graph
    //if all vertices are connected then return 1
    public int getNumberOfConnectedComponent(int vertices, int[][] edges){
        UnionFind unionFind = new UnionFind(vertices);
        int connectedGraph  = vertices;
        for(int[] edge:edges){
            if(unionFind.union(edge[0], edge[1])){
                connectedGraph--;
            }
        }
        return connectedGraph;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1}, {0,2}};
        System.out.println("Expected = 1 # "+new ConnectedGraph().getNumberOfConnectedComponent(3, edges));
        int[][] edges2 = {{0,1},{1,2},{2,3},{4,5}};
        System.out.println("Expected = 2 # "+new ConnectedGraph().getNumberOfConnectedComponent(6, edges2));
    }
}
