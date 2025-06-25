package graph.topological_sort;

import java.util.*;

//  1 -> 3  ->  4
//       ^           \
//       |            >  0
//       2  ->  5   /

public class CourseSchedule {
    // [[a,b],[],[] ...] means to start course a you have to finish course b first
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] indegree = new int[numCourses]; //indegree of 3 & 0 is 2
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i=0; i<numCourses; i++){
            adjList.add(new ArrayList<>()); // initialize all adjacency list
        }
        //calculate indegree and adjList
        //[[3,1],[3,2],[4,3],[5,2],[0,4],[0,5]]
        for(int[] prerequisite : prerequisites){
            int course = prerequisite[0];
            int dependent_course = prerequisite[1];
            indegree[course]++;                         // [2,0,0,2,1,1]
            adjList.get(dependent_course).add(course);  //[[0->],[1->[3]],[2->[3,5],[3->[4]],[4->[0]],[5->[0]]]]
        }
        //find number of course a person can complete or if there is any loop
        List<Integer> resultCourse = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        //assign all course has no dependency to start with
        for(int i=0; i<indegree.length; i++){
            if(indegree[i] == 0){ // indegree 0 means it's independent course
                queue.offer(i);
            }
        }
        // queue is having 1 & 2 as of now
        while(!queue.isEmpty()){
            int node = queue.poll();
            resultCourse.add(node); // add nodes into the result
            for(Integer nextNode : adjList.get(node)){  // take adjacency nodes of current node
                indegree[nextNode]--;   // decrese indegree of nextNode
                if(indegree[nextNode] == 0){    // if nextNode indegree become 0 then assign in queue
                    queue.offer(nextNode);
                }
            }
        }
        return numCourses == resultCourse.size();
    }

    public static void main(String[] args) {
        int[][] preRequisites = {{3,1},{3,2},{4,3},{5,2},{0,4},{0,5}};
        boolean result = canFinish(6, preRequisites);
        System.out.println(result);
    }
}
