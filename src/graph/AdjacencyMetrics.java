package graph;

public class AdjacencyMetrics {
    
    public void addEdgeDirected(boolean[][] metrics, int vertex1, int vertex2){
        metrics[vertex1][vertex2] = true;
    }
    public void addEdgeUndirected(boolean[][] metrics, int vertex1, int vertex2){
        metrics[vertex1][vertex2] = true;
        metrics[vertex2][vertex1] = true;
    }
    public void addEdgeUndirectedWeighted(int[][] metrics, int vertex1, int vertex2, int weight){
        metrics[vertex1][vertex2] = weight;
        metrics[vertex2][vertex1] = weight;
    }
    public void removeEdgeDirected(boolean[][] metrics, int vertex1, int vertex2) {
        metrics[vertex1][vertex2] = false;
    }
    public void removeEdgeUndirectedWeighted(int[][] metrics, int vertex1, int vertex2){
        metrics[vertex1][vertex2] = 0;
        metrics[vertex2][vertex1] = 0;
    }
    public void removeEdgeUndirected(boolean[][] metrics, int vertex1, int vertex2){
        metrics[vertex1][vertex2] = false;
        metrics[vertex2][vertex1] = false;
    }

    public void removeVertexUndirectedAndDirected(boolean[][] metrics, int vertex){
        for(int i=0; i<metrics[vertex].length; i++){
            metrics[vertex][i] = false;
        }
        for(int i=0; i<metrics.length; i++){
            metrics[i][vertex] = false;
        }
    }
    public void removeVertexUndirectedAndDirectedWeighted(int[][] metrics, int vertex){
        for(int i=0; i<metrics[vertex].length; i++){
            metrics[vertex][i] = 0;
        }
        for(int i=0; i<metrics.length; i++){
            metrics[i][vertex] = 0;
        }
    }

    public static void main(String[] args) {
        AdjacencyMetrics adjacencyMetrics = new AdjacencyMetrics();
        //adjacencyMetrics.testUndirectedAdjacencyMetrics();
        //adjacencyMetrics.testDirectedAdjacencyMetrics();
        adjacencyMetrics.testUndirectedWeightedAdjacencyMetrics();
    }
    private void testDirectedAdjacencyMetrics() {
        boolean[][] metrics = new boolean[4][4];
        this.addEdgeDirected(metrics, 0, 1);
        this.addEdgeDirected(metrics, 0, 2);
        this.addEdgeDirected(metrics, 1, 2);
        this.addEdgeDirected(metrics, 1, 3);
        this.addEdgeDirected(metrics, 2, 3);
        this.addEdgeDirected(metrics, 3, 0);
        this.showGraph(metrics);
        System.out.println("---- After Delete edge 1, 2 ---- ");
        this.removeEdgeDirected(metrics, 1, 2);
        this.showGraph(metrics);
        System.out.println("---- After Delete vertex 3 ---- ");
        this.removeVertexUndirectedAndDirected(metrics, 3);
        this.showGraph(metrics);
    }
    private void testUndirectedAdjacencyMetrics(){
        boolean[][] metrics = new boolean[4][4];
        this.addEdgeUndirected(metrics, 0,1);
        this.addEdgeUndirected(metrics, 0,2);
        this.addEdgeUndirected(metrics, 1,2);
        this.addEdgeUndirected(metrics, 1,3);
        this.addEdgeUndirected(metrics, 2,3);
        this.addEdgeUndirected(metrics, 2,1);
        this.showGraph(metrics);
        System.out.println("---- After Delete edge 3, 2 ---- ");
        this.removeEdgeUndirected(metrics, 3, 2);
        this.showGraph(metrics);
        System.out.println("---- After Delete vertex 1 ---- ");
        this.removeVertexUndirectedAndDirected(metrics, 1);
        this.showGraph(metrics);
    }
    private void testUndirectedWeightedAdjacencyMetrics(){
        int[][] metrics = new int[4][4];
        this.addEdgeUndirectedWeighted(metrics, 0,1, 5);
        this.addEdgeUndirectedWeighted(metrics, 0,2, 4);
        this.addEdgeUndirectedWeighted(metrics, 1,2, 3);
        this.addEdgeUndirectedWeighted(metrics, 1,3, 5);
        this.addEdgeUndirectedWeighted(metrics, 2,3, 7);
        this.showGraph(metrics);
        System.out.println("---- After Delete edge 3, 2 ---- ");
        this.removeEdgeUndirectedWeighted(metrics, 3, 2);
        this.showGraph(metrics);
        System.out.println("---- After Delete vertex 1 ---- ");
        this.removeVertexUndirectedAndDirectedWeighted(metrics, 1);
        this.showGraph(metrics);
    }
    private void showGraph(boolean[][] metrics){
        for(int row=0; row<metrics.length; row++){
            System.out.print(row+"'s neighbours are ");
            for(int col = 0; col<metrics[row].length; col++){
                if(metrics[row][col]){
                    System.out.print(col+" ");
                }
            }
            System.out.println();
        }
    }
    private void showGraph(int[][] metrics){
        for(int row=0; row<metrics.length; row++){
            System.out.print(row+"'s neighbours are ");
            for(int col = 0; col<metrics[row].length; col++){
                if(metrics[row][col] !=0){
                    System.out.print(col+"("+metrics[row][col]+") ");
                }
            }
            System.out.println();
        }
    }
}
