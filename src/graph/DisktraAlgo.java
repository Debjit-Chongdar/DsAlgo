package graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class DisktraAlgo {
    private List<Vertex> vertices = new ArrayList<>();
    public void addVertex(Vertex vertex){
        vertices.add(vertex);
    }
    public void addEdge(Vertex source, Vertex target, double distance){
        Edge edge = new Edge(source, target, distance);
        source.neighbours.add(edge);
    }
    public void calculateShortestPath(Vertex source){
        source.distance=0.0;
        PriorityQueue<Vertex> pQueue = new PriorityQueue<>();
        pQueue.offer(source);
        while(!pQueue.isEmpty()){
            Vertex u = pQueue.poll();
            //u.isVisited=true; -- this will protect to re compute
            List<Edge> neighbours = u.neighbours;
            for(Edge edge : neighbours){
                Vertex target = edge.target;
                //if(!target.isVisited){ -- this will protect to recompute
                    pQueue.remove(target);//if already present then remove
                    if(target.distance > (u.distance + edge.distance)){
                        target.previousVertex = u;
                        target.distance = u.distance + edge.distance;
                    }
                    pQueue.offer(target);
                //}
            }
        }
    }

    public static void main(String[] args) {
        DisktraAlgo test = new DisktraAlgo();
        Vertex source = createAllVertexAndEdges(test);
        test.calculateShortestPath(source);
        printDistanceAndPath(test.vertices.get(1));
        printDistanceAndPath(test.vertices.get(3));
        printDistanceAndPath(test.vertices.get(5));
        printDistanceAndPath(test.vertices.get(6));
    }
    private static Vertex createAllVertexAndEdges(DisktraAlgo test){
        Vertex a = new Vertex("A");
        Vertex b = new Vertex("B");
        Vertex c = new Vertex("C");
        Vertex d = new Vertex("D");
        Vertex e = new Vertex("E");
        Vertex f = new Vertex("F");
        Vertex g = new Vertex("G");
        Vertex h = new Vertex("H");
        test.addVertex(a);
        test.addVertex(b);
        test.addVertex(c);
        test.addVertex(d);
        test.addVertex(e);
        test.addVertex(f);
        test.addVertex(g);
        test.addVertex(h);
        test.addEdge(a,b, 5);
        test.addEdge(a,h,8);
        test.addEdge(a, e, 9);
        test.addEdge(b,d,15);
        test.addEdge(b,c,12);
        test.addEdge(b,h, 4);
        test.addEdge(h,c,7);
        test.addEdge(h,f, 6);
        test.addEdge(e, f, 4);
        test.addEdge(e,g, 20);
        test.addEdge(d, g, 9);
        test.addEdge(c, d, 3);
        test.addEdge(c, g, 11);
        test.addEdge(f, c, 1);
        test.addEdge(f,g,13);
        //test.addEdge(g,b,12); // if we have cycle in path
        return a;
    }
    static void printDistanceAndPath(Vertex target){
        System.out.println("Distance = (A -> "+target.name +") : "+target.distance);
        System.out.print("Path = ");
        printPath(target);
        System.out.println();
    }
    private static void printPath(Vertex target){
        if(target == null){
            return;
        }
        printPath(target.previousVertex);
        System.out.print(target.name+" -> ");
    }
}
