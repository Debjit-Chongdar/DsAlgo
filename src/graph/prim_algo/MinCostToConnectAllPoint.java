package graph.prim_algo;

import java.util.*;

//if Vertices are less use prim algo  =>  O((V + E) log V)  or  O(V²)

// We have few vertices position, which can connect each other, find the smallest edge to connect all vertices
// difference between two vertices is the cost/weight of the edge
//[[0,0],[2,2],[3,3],[2,4],[4,2]]
public class MinCostToConnectAllPoint {
    public static int primAlgo(int[][] vertices) {
        Map<Integer, List<int[]>> adjList = populateAdjacencyList(vertices);
        PriorityQueue<int[]> minHeapOnCost = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        minHeapOnCost.offer(new int[]{0, 0});
        int cost = 0;
        Set<Integer> visitedVertices = new HashSet<>();
        while (visitedVertices.size() < vertices.length) {
            int[] vertexCost = minHeapOnCost.poll();
            int vertex = vertexCost[0];
            int curCost = vertexCost[1];
            if (visitedVertices.contains(vertex)) {
                continue;
            }
            visitedVertices.add(vertex);
            cost += curCost;
            for (int[] neighbour : adjList.get(vertex)) {
                if (!visitedVertices.contains(neighbour[0])) { // this check is optional as we are checking before
                    minHeapOnCost.offer(neighbour);
                }
            }
        }
        return cost;
    }

    private static Map<Integer, List<int[]>> populateAdjacencyList(int[][] vertices) {
        Map<Integer, List<int[]>> adjList = new HashMap<>();
        for (int i = 0; i < vertices.length; i++) {
            int x1 = vertices[i][0]; //x axis
            int y1 = vertices[i][1]; // y axis
            for (int j = i + 1; j < vertices.length; j++) {
                int x2 = vertices[j][0];
                int y2 = vertices[j][1];
                int cost = Math.abs(x1 - x2) + Math.abs(y1 - y2);
                adjList.computeIfAbsent(i, key -> new ArrayList<>()).add(new int[]{j, cost});
                adjList.computeIfAbsent(j, key -> new ArrayList<>()).add(new int[]{i, cost});
            }
        }
        return adjList;
    }

    public static void main(String[] args) {
        System.out.println("expected 10 # " + primAlgo(new int[][]{{0, 0}, {2, 2}, {3, 3}, {2, 4}, {4, 2}}));
    }
}
//                4
//                !
//                !
//  0 ------------1 ------ 2
//                !
//                !
//                3