package graph;

import java.util.*;

public class BFS {
    static List<Vertex> vertices = new ArrayList<>();
    
    public static void travers(Vertex start){
        Queue<Vertex> vertexQueue = new LinkedList<>();
        vertexQueue.offer(start);
        start.isVisited = true;
        while(!vertexQueue.isEmpty()){
            Vertex vertex = vertexQueue.poll();
            System.out.print(vertex.name + " ");
            for(Edge edge : vertex.neighbours){
                if(!edge.target.isVisited){
                    edge.target.isVisited = true;
                    vertexQueue.offer(edge.target);
                }
            }
        }
    }
    public static void traversWithoutUsingExistingIsVisited(Vertex start){
        Set<Vertex>  visitedVertex = new HashSet<>();
        visitedVertex.add(start);
        Queue<Vertex> vertexQueue = new LinkedList<>();
        vertexQueue.offer(start);
        while(!vertexQueue.isEmpty()){
            Vertex vertex = vertexQueue.poll();
            System.out.print(vertex.name + " ");
            for(Edge edge : vertex.neighbours){
                if(!visitedVertex.contains(edge.target)){
                    visitedVertex.add(edge.target);
                    vertexQueue.offer(edge.target);
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

        a.neighbours.add(new Edge(a,b, 5));
        a.neighbours.add(new Edge(a,h,8));
        a.neighbours.add(new Edge(a, e, 9));
        b.neighbours.add(new Edge(b,d,15));
        b.neighbours.add(new Edge(b,c,12));
        b.neighbours.add(new Edge(b,h, 4));
        h.neighbours.add(new Edge(h,c,7));
        h.neighbours.add(new Edge(h,f, 6));
        e.neighbours.add(new Edge(e, f, 4));
        e.neighbours.add(new Edge(e,g, 20));
        d.neighbours.add(new Edge(d, g, 9));
        c.neighbours.add(new Edge(c, d, 3));
        c.neighbours.add(new Edge(c, g, 11));
        f.neighbours.add(new Edge(f, c, 1));
        f.neighbours.add(new Edge(f,g,13));
        //test.addEdge(g,b,12); // if we have cycle in path
        travers(a);
        System.out.println();
        traversWithoutUsingExistingIsVisited(a);
    }
}
