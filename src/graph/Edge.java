package graph;

public class Edge {
    public Vertex source;
    public Vertex target;
    public double distance;
    public Edge(Vertex source, Vertex target, double distance){
        this.source = source;
        this.target = target;
        this.distance = distance;
    }
}
