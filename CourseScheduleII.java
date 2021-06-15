import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[] d = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] p : prerequisites) {
            d[p[0]]++;
            map.get(p[1]).add(p[0]);
        }
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < d.length; i++) {
            if (d[i] == 0) {
                q.offer(i);
            }
        }
        List<Integer> res = new ArrayList<>();
        while (q.size() > 0) {
            int cur = q.poll();
            res.add(cur);
            for (int nei : map.get(cur)) {
                if (d[nei] == 1) {
                    q.offer(nei);
                }
                d[nei]--;
            }
        }
        if (res.size() != numCourses) {
            return new int[0];
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
    // time: O(n) space: O(n)
}
