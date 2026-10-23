import java.util.*;

public class CourseSchedule_207 {

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //pre[] = {corse,pre}
        //adjlist
        List<List<Integer>> graph = new ArrayList<>();
        for(int i=0;i<numCourses;i++){
            graph.add(new ArrayList<>());
        }
        for(int[] innerListOFPrereq : prerequisites){
            int course = innerListOFPrereq[0];
            int preReq = innerListOFPrereq[1];

            //prereq given = [1,0] means 0->1 edge so add in reverse
            graph.get(preReq).add(course);
        }

        int[] indegree = new int[numCourses];
        for(int i=0;i<indegree.length;i++){
            for(int neighbor : graph.get(i)){
                indegree[neighbor]++;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int count=0;
        for(int i=0;i<indegree.length;i++){
            if(indegree[i] == 0){
                //add to q
                q.offer(i);
            }
        }
        while(!q.isEmpty()){
            int node = q.poll();
            count++;

            for(int neighbor : graph.get(node)){

                indegree[neighbor]--;

                if(indegree[neighbor] == 0 ){
                    q.offer(neighbor);
                }
            }
        }

        return count == numCourses;
    }
}
