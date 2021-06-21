import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class CheapestFlightsWithinKStops {
    // M1: dijkstra (bfs + pq) - time: O(NlogN), N = const * v, V can be more with duplicate, space: O(const * V)
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, List<int[]>> map = new HashMap<>();// from, list of <to, price>
        // build graph
        for (int i = 0; i < n; i++) {
            map.put(i, new ArrayList<>());
        }
        for (int[] flight : flights) {
            map.get(flight[0]).add(new int[]{flight[1], flight[2]});
        }
        // bfs
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        return bfs(src, dst, k, dist, map);
    }

    private int bfs(int src, int dst, int k, int[] dist, Map<Integer, List<int[]>> map) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);// node, cnt, total price -> small price first
        pq.offer(new int[]{src, 0, 0});
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            int node = cur[0], cnt = cur[1], totalPrice = cur[2];
            if (node == dst) {
                return totalPrice;
            }
            if (cnt == k + 1) continue;
            if (cnt >= dist[node]) continue;
            dist[node] = cnt;
            for (int[] nei : map.get(node)) {
                pq.offer(new int[]{nei[0], cnt + 1, nei[1] + totalPrice});
            }
        }
        return -1;
    }
}
