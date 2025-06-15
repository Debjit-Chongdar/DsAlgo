package graph.dijkstra_algo;

import java.util.*;

public class MaximumProfitability {
    static class Pair{
        int node;
        double profitability;
        public Pair(int node, double profitability){
            this.node = node;
            this.profitability = profitability;
        }
    }
    public static double maxProbability(int n, int[][] edges, double[] succProb, int start_node, int end_node) {
        PriorityQueue<Pair> priorityQueue = new PriorityQueue<>((a,b) -> Double.compare(b.profitability, a.profitability));
        Map<Integer, List<Pair>> adjList = new HashMap<>();
        for(int i=0; i<edges.length; i++){
            int[] edge = edges[i];
            double prof = succProb[i];
            adjList.computeIfAbsent(edge[0], key -> new ArrayList<>()).add(new Pair(edge[1], prof));
            adjList.computeIfAbsent(edge[1], key -> new ArrayList<>()).add(new Pair(edge[0], prof));
        }
        if(adjList.containsKey(start_node)){
            boolean[] isVisit = new boolean[n];
            isVisit[start_node] = true;
            priorityQueue.offer(new Pair(start_node, 1));
            while (!priorityQueue.isEmpty()){
                Pair pair = priorityQueue.poll();
                if(pair.node == end_node){
                    return pair.profitability;
                }
                for(Pair pair1 : adjList.get(pair.node)){
                    if(!isVisit[pair1.node]){
                        //priorityQueue.offer(new Pair(pair1.node, pair1.profitability * pair.profitability));
                        pair1.profitability = pair1.profitability * pair.profitability;
                        priorityQueue.offer(pair1);
                    }
                }
            }
        }
        return 0.0;
    }

    public static void main(String[] args) {
        System.out.println(maxProbability(3, new int[][]{{0,1},{1,2},{0,2}}, new double[]{0.5,0.5,0.2},0,2));
        System.out.println(maxProbability(3, new int[][]{{0,1}}, new double[]{0.5},0,2));
        System.out.println(maxProbability(3, new int[][]{{0,1},{1,2},{0,2}}, new double[]{0.5,0.5,0.3},0,2));
    }
}
