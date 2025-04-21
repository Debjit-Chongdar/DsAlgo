package graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex implements Comparable<Vertex>{
    public String name;
    public double weight;
    public Vertex previousVertex;
    public boolean isVisited;
    public List<Edge> neighbours;
    public Vertex(String name){
        this.name = name;
        this.weight = Double.MAX_VALUE;
        this.neighbours = new ArrayList<>();
    }
    @Override
    public int compareTo(Vertex vertex){
        return Double.compare(this.weight, vertex.weight);
    }
}
