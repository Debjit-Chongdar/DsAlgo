package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static graph.DijkstraAlgo.printPath;

public class BellmanFordAlgo {
    private final List<Vertex> vertices = new ArrayList<>();
    private final Set<Edge> edges = new HashSet<>();

    public void addEdge(Vertex source, Vertex target, double distance){
        edges.add(new Edge(source, target, distance));
    }
    public void computeShortestPath(Vertex start){
        start.weight = 0.0;
        for(int i=1; i<vertices.size(); i++){
            for(Edge edge : edges){
                Vertex source = edge.source;
                Vertex target = edge.target;
                if(source.weight+edge.distance < target.weight){
                    target.weight = source.weight+edge.distance;
                    target.previousVertex = source;
                }
            }
        }
        for(Edge edge : edges){
            if(edge.source.weight != Double.MAX_VALUE){
                if(hascycle(edge)){
                    throw new RuntimeException("Graph has a cycle");
                }
            }
        }
    }
    private boolean hascycle(Edge edge){
        return edge.source.weight+edge.distance < edge.target.weight;
    }

    public static void main(String[] args) {
        BellmanFordAlgo fordAlgo = new BellmanFordAlgo();
        Vertex start = fordAlgo.populateVerticesAndEdges();
        fordAlgo.computeShortestPath(start);
        fordAlgo.showDistanceAndPath(fordAlgo.vertices.get(3));
        fordAlgo.showDistanceAndPath(fordAlgo.vertices.get(5));
        fordAlgo.showDistanceAndPath(fordAlgo.vertices.get(6));
    }

    private void showDistanceAndPath(Vertex vertex) {
        System.out.println("Distance (A -> "+vertex.name+") : "+vertex.weight);
        printPath(vertex);
        System.out.println();
    }

    private Vertex populateVerticesAndEdges() {
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");
        this.vertices.add(a);
        this.vertices.add(b);
        this.vertices.add(c);
        this.vertices.add(d);
        this.vertices.add(e);
        this.vertices.add(f);
        this.vertices.add(g);
        this.vertices.add(h);
        this.addEdge(a,b, 5);
        this.addEdge(a,h,8);
        this.addEdge(a, e, 9);
        this.addEdge(b,d,15);
        this.addEdge(b,c,12);
        this.addEdge(b,h, 4);
        this.addEdge(h,c,7);
        this.addEdge(h,f, 6);
        this.addEdge(e, f, 4);
        this.addEdge(e,g, 20);
        this.addEdge(d, g, 9);
        this.addEdge(c, d, 3);
        this.addEdge(c, g, 11);
        this.addEdge(f, c, 1);
        this.addEdge(f,g,13);
        //this.addEdge(g,b,12); // if we have cycle in path
        return a;
    }
}
