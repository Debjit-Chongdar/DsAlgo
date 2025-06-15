package graph.dijkstra_algo;

import java.util.*;

public class NetworkDelayTime {
    private Map<Integer, List<int[]>> adjList;

    //max time to reach all node
    public int findNetworkDelayTime(int[][] edges, int nodes, int startNode){
        populateAdjList(edges);
        PriorityQueue<int[]> queue = new PriorityQueue<>((a,b) -> a[1]-b[1]);
        Set<Integer> isVisited = new HashSet<>();
        int time = 0;//this is the one which calculated last, means has more weight to reach
        queue.offer(new int[]{startNode, 0});
        while (!queue.isEmpty()){
            int[] nodeWeight = queue.poll();
            int node = nodeWeight[0];
            int weight = nodeWeight[1];
            if(isVisited.contains(node)){ // if current node is already visited means, the node has way to reach with less weight
                continue;
            }
            isVisited.add(node);
            time = weight; // always updated with latest value, max weight to reach
            if(adjList.containsKey(node)){
                for(int[] edge : adjList.get(node)){
                    if(!isVisited.contains(edge[0])){ //if it's already visited means, it has less weight to reach
                        queue.offer(new int[]{edge[0], weight+edge[1]}); //add existing weight with current weight
                    }
                }
            }
        }
        return (isVisited.size() == nodes) ? time : -1;
    }

    private void populateAdjList(int[][] edges) {
        adjList = new HashMap<>();
        for(int[] edge : edges){
            adjList.computeIfAbsent(edge[0], key ->new ArrayList<int[]>()).add(new int[]{edge[1], edge[2]});
        }
    }

    public static void main(String[] args) {
        int[][] edges = {
                {1,2,1},
                {2,3,1},
                {1,4,4},
                {3,4,1}
        };
        NetworkDelayTime nd = new NetworkDelayTime();
        System.out.println(nd.findNetworkDelayTime(edges, 4, 1));
        System.out.println(nd.findNetworkDelayTime(edges, 4, 2));
        int[][] edges2 = {
                {1,2,1},
                {1,3,4},
                {2,3,2},
                {2,4,1},
                {4,3,2}
        };
        System.out.println(nd.findNetworkDelayTime(edges2, 4, 1));
    }
}
