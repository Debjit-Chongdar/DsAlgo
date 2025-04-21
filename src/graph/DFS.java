package graph;

import java.util.*;

public class DFS {
    static List<Vertex> vertices = new ArrayList<>();
    public static void addEdge(Vertex u, Vertex v, double distance){
        u.neighbours.add(new Edge(u, v, distance));
    }

    public static void travers(Vertex start){
        Stack<Vertex> vertexStack = new Stack<>();
        vertexStack.push(start);
        start.isVisited = true;
        while(!vertexStack.isEmpty()){
            Vertex vertex = vertexStack.pop();
            System.out.print(vertex.name +" ");
            for(Edge edge : vertex.neighbours){
                if(!edge.target.isVisited){
                    edge.target.isVisited = true;
                    vertexStack.push(edge.target);
                }
            }
        }
    }

    public static void main(String[] args) {
        testTravers();
    }
    private static void testTravers(){
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");
        vertices.add(a);
        vertices.add(b);
        vertices.add(c);
        vertices.add(d);
        vertices.add(e);
        vertices.add(f);
        vertices.add(g);
        vertices.add(h);
        
        addEdge(a,b, 5);
        addEdge(a,h,8);
        addEdge(a, e, 9);
        addEdge(b,d,15);
        addEdge(b,c,12);
        addEdge(b,h, 4);
        addEdge(h,c,7);
        addEdge(h,f, 6);
        addEdge(e, f, 4);
        addEdge(e,g, 20);
        addEdge(d, g, 9);
        addEdge(c, d, 3);
        addEdge(c, g, 11);
        addEdge(f, c, 1);
        addEdge(f,g,13);
        addEdge(g,b,12); // if we have cycle in path
        travers(a);
    }
}
