import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

public class CourseSchedule {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();// <source, list of destinations>
        int[] inDegree = new int[numCourses];
        for (int[] p : prerequisites) {
            inDegree[p[0]]++;
            if (!map.containsKey(p[1])) {
                map.put(p[1], new ArrayList<>());
            }
            map.get(p[1]).add(p[0]);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        int cnt = 0;
        while (q.size() > 0) {
            int cur = q.poll();
            cnt++;
            List<Integer> neighbors = map.get(cur);
            if (neighbors != null) {
                for (int nei : neighbors) {
                    if (inDegree[nei] == 1) {
                        q.offer(nei);
                    }
                    inDegree[nei]--;
                }
            }
        }
        return cnt == numCourses;
    }
    // time: O(n) space: O(n)
}
