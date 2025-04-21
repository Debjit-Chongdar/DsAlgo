package graph;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AdjacencyList {

    public void insert(List<AbstractMap.SimpleEntry<String, List<String>>> vertices, String name){
        vertices.forEach(vertex -> {
            if(vertex.getKey().equals(name)){
                throw new IllegalArgumentException("Vertex with same name already present");
            }
        });
        vertices.add(new AbstractMap.SimpleEntry<>(name, new ArrayList<>()));
    }
    public void createEdge(List<List<Integer>> vertices, int vertex1, int vertex2){
        if(vertices.size()>vertex1 && !vertices.get(vertex1).contains(vertex2)){
            vertices.get(vertex1).add(vertex2);
        }
        if(vertices.size()>vertex2 && !vertices.get(vertex2).contains(vertex1)){
            vertices.get(vertex2).add(vertex1);
        }
    }
    public void createEdge(List<AbstractMap.SimpleEntry<String, List<String>>> vertices, String vertex1, String vertex2){
        vertices.forEach(vertex -> {
            if(vertex.getKey().equals(vertex1) && !vertex.getValue().contains(vertex2)){
                vertex.getValue().add(vertex2);
            }
            if(vertex.getKey().equals(vertex2) && !vertex.getValue().contains(vertex1)){
                vertex.getValue().add(vertex1);
            }
        });
    }
    public void delete(List<List<Integer>> vertices, int vertex){
        if(vertices.get(vertex) != null || !vertices.get(vertex).isEmpty()){
            vertices.set(vertex, new ArrayList<>());
        }
        for(int i=0; i<vertices.size(); i++){
            List<Integer> neighbours = vertices.get(i);
            if(neighbours.contains(vertex)){
                int position = 0;
                while(position<neighbours.size()){
                    if(neighbours.get(position) == vertex){
                        break;
                    }
                    position++;
                }
                neighbours.remove(position);
            }
        }
    }
    public void delete(List<AbstractMap.SimpleEntry<String, List<String>>> vertices, String vertex) {
        //avoid concurrent modification exception
        List<AbstractMap.SimpleEntry> simpleEntry = vertices.stream()
                .filter(entry -> entry.getKey().equals(vertex)).collect(Collectors.toList());
        if(null != simpleEntry && !simpleEntry.isEmpty()){
            vertices.remove(simpleEntry.get(0)); //avoid concurrent modification exception
        }
        vertices.forEach(entry -> {
            List<String> values = new ArrayList<>(entry.getValue()); //avoid concurrent modification exception
            entry.getValue().forEach(neighbour -> {
                if (neighbour.equals(vertex)) {
                    values.remove(vertex);
                }
            });
            entry.setValue(values); //avoid concurrent modification exception
        });
    }

    public static void main(String[] args) {
        AdjacencyList adjacencyList = new AdjacencyList();
        //adjacencyList.testStringAdjacencyList();
        adjacencyList.testAdjacencyList();
    }
    private void testAdjacencyList(){
        List<List<Integer>> vertices = new ArrayList<>();
        vertices.add(new ArrayList<>());
        vertices.add(new ArrayList<>());
        vertices.add(new ArrayList<>());
        vertices.add(new ArrayList<>());
        vertices.add(new ArrayList<>());
        createEdge(vertices, 0,1);
        createEdge(vertices, 0,2);
        createEdge(vertices, 0,4);
        createEdge(vertices, 1,3);
        createEdge(vertices, 1,4);
        createEdge(vertices, 2,4);
        createEdge(vertices, 3,4);
        printAdjList(vertices);
        System.out.println("After delete vertex 2");
        delete(vertices, 2);
        printAdjList(vertices);
    }
    private void testStringAdjacencyList(){
        List<AbstractMap.SimpleEntry<String, List<String>>> vertices = new ArrayList<>();
        insert(vertices,"A");
        insert(vertices,"B");
        insert(vertices,"C");
        insert(vertices,"D");
        insert(vertices,"E");
        insert(vertices,"F");
        insert(vertices,"G");
        insert(vertices,"H");

        createEdge(vertices,"A", "B");
        createEdge(vertices,"A", "H");
        createEdge(vertices,"A", "E");
        createEdge(vertices,"B", "D");
        createEdge(vertices,"B", "C");
        createEdge(vertices,"B", "H");
        createEdge(vertices,"H", "C");
        createEdge(vertices,"H", "F");
        createEdge(vertices,"E", "F");
        createEdge(vertices,"E", "G");
        createEdge(vertices,"D", "G");
        createEdge(vertices,"A", "B");
        createEdge(vertices,"C", "D");
        createEdge(vertices,"C", "G");
        createEdge(vertices,"F", "C");
        createEdge(vertices,"F", "G");
        printAdjacencyList(vertices);
        System.out.println("--------- Delete C ----------");
        delete(vertices,"C");
        printAdjacencyList(vertices);
    }
    private void printAdjList(List<List<Integer>> vertices){
        for(int i=0; i<vertices.size(); i++){
            System.out.print("Vertex = "+i+" , Neighbours = "+ vertices.get(i));
            System.out.println();
        }
    }
    private void printAdjacencyList(List<AbstractMap.SimpleEntry<String, List<String>>> vertices) {
        vertices.forEach(entry -> {
            System.out.print("Vertex = "+entry.getKey() +" ,Neighbours = ");
            entry.getValue().forEach(vertex -> {
                System.out.print(vertex);
            });
            System.out.println();
        });
    }
}
