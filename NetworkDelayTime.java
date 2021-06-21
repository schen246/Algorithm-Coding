import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class NetworkDelayTime {
    // M1: dijkstra (bfs + pq) - time: O(ElogE) E is number of edges, space: O(E) since each node can be added many times
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();
        // build graph
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] time : times) {
            map.get(time[0]).add(new int[]{time[1], time[2]});
        }
        // bfs
        return bfs(k, n, map);
    }
    
    private int bfs(int src, int cnt, Map<Integer, List<int[]>> map) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);// node, total time
        Set<Integer> set = new HashSet<>();
        pq.offer(new int[]{src, 0});
        int totalTime = 0;
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            int node = cur[0];
            if (!set.add(node)) continue;
            totalTime = cur[1];
            for (int[] nei : map.get(node)) {
                pq.offer(new int[]{nei[0], nei[1] + totalTime});
            }
        }
        return set.size() == cnt ? totalTime : -1;
    }
}
