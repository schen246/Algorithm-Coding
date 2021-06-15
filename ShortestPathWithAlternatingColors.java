import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class ShortestPathWithAlternatingColors {
    // bfs - time: O(V + E) space: O(V + E)
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        Map<Integer, List<Integer>> redMap = new HashMap<>();
        Map<Integer, List<Integer>> blueMap = new HashMap<>();
        buildGraph(n, red_edges, blue_edges, redMap, blueMap);
        // bfs
        int RED = 0, BLUE = 1;
        Queue<int[]> q = new ArrayDeque<>();
        int[][] dist = new int[n][2];
        for (int i = 0; i < n; i++) {
            dist[i][0] = Integer.MAX_VALUE;
            dist[i][1] = Integer.MAX_VALUE;
        }
        q.offer(new int[]{0, RED});//red
        dist[0][RED] = 0;
        q.offer(new int[]{0, BLUE});//blue
        dist[0][BLUE] = 0;
        while (q.size() > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                int node = cur[0], color = cur[1], nextColor = color ^ 1;
                List<Integer> nextEdges = null;
                if (nextColor == RED) {
                    nextEdges = redMap.get(node);
                } else {
                    nextEdges = blueMap.get(node);
                }
                for (int nextNode : nextEdges) {
                    if (dist[nextNode][nextColor] == Integer.MAX_VALUE) {
                        q.offer(new int[]{nextNode, nextColor});
                        dist[nextNode][nextColor] = dist[node][color] + 1;
                    }
                }
            }
        }
        int[] res = new int[n];
        for (int node = 0; node < n; node++) {
            if (dist[node][RED] == Integer.MAX_VALUE && dist[node][BLUE] == Integer.MAX_VALUE) {
                res[node] = -1;
            } else {
                res[node] = Math.min(dist[node][RED], dist[node][BLUE]);
            }
        }
        return res;
    }
    
    private void buildGraph(int n, int[][] red_edges, int[][] blue_edges, Map<Integer, List<Integer>> redMap, Map<Integer, List<Integer>> blueMap) {
        for (int i = 0; i < n; i++) {
            redMap.put(i, new ArrayList<>());
            blueMap.put(i, new ArrayList<>());
        }
        for (int[] red : red_edges) {
            redMap.get(red[0]).add(red[1]);
        }
        for (int[] blue : blue_edges) {
            blueMap.get(blue[0]).add(blue[1]);
        }
    }
}
