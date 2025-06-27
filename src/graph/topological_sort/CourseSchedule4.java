package graph.topological_sort;

import java.util.*;

public class CourseSchedule4 {
    // find the course relation, child parent relationship
    //numCourses = 4, prerequisites = [[1,0],[2,1],[3,2]], queries = [[0,1],[3,1]]
    //Output: [false,true] => for 1 0 is prerequisite not the vice versa
    public static List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int[] indegree = new int[numCourses];
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        for(int[] pre : prerequisites){
            indegree[pre[0]]++;
            adjList.computeIfAbsent(pre[1], key-> new ArrayList<>()).add(pre[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<indegree.length; i++){
            if(indegree[i] == 0){
                queue.offer(i);
            }
        }
        //this part will only work when the flow will go from parent to child direction
        Map<Integer, Set<Integer>> parentMap = new HashMap<>();
        while(!queue.isEmpty()){
            int node = queue.poll();
            if(adjList.containsKey(node)) {
                for (int neighbour : adjList.get(node)) {
                    parentMap.computeIfAbsent(neighbour, key -> new HashSet<>()).add(node);
                    if (parentMap.containsKey(node)) {
                        parentMap.get(neighbour).addAll(parentMap.get(node));
                    }
                    indegree[neighbour]--;
                    if (indegree[neighbour] == 0) {
                        queue.offer(neighbour);
                    }
                }
            }
        }
        //check the query
        List<Boolean> result = new ArrayList<>();
        for(int[] query : queries){
            int child = query[0];
            int parent = query[1];
            if(parentMap.containsKey(child)) {
                result.add(parentMap.get(child).contains(parent));
            }else{
                result.add(false);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int numCourses = 4;
        int[][] prerequisites = {{1,0},{2,1},{3,2}};
        int[][] queries = {{0,1},{3,1}};
        List<Boolean> result = checkIfPrerequisite(numCourses, prerequisites, queries);
        result.forEach(System.out::println);
        System.out.println("--------------------------");
        int numCourses1 = 6;
        int[][] prerequisites1 = {{3,2},{2,0},{5,1},{4,2},{2,1}};
        int[][] queries1 = {{4,1},{5,1},{3,5},{3,0}};
        List<Boolean> result1 = checkIfPrerequisite(numCourses1, prerequisites1, queries1);
        result1.forEach(System.out::println);
    }
}
