import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class TheMazeII {
    // bfs + pq - time: O(mnlogmn) space: O(mn)
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        int m = maze.length, n = maze[0].length;
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return 0;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[2] - b[2]);// min heap
        Set<Integer> set = new HashSet<>();
        pq.offer(new int[]{start[0], start[1], 0});// x,y,distance
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            if (!set.add(cur[0] * n + cur[1])) {
                continue;// dedup
            }
            if (cur[0] == destination[0] && cur[1] == destination[1]) {
                return cur[2];
            }
            for (int[] nei : getNei(maze, cur)) {
                pq.offer(nei);
            }
        }
        return -1;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private List<int[]> getNei(int[][] maze, int[] cur) {
        List<int[]> res = new ArrayList<>();
        for (int[] dir : DIRS) {
            int x = cur[0] + dir[0], y = cur[1] + dir[1], cnt = 0;
            while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
                cnt++;
            }
            if (x - dir[0] != cur[0] || y - dir[1] != cur[1]) {
                res.add(new int[]{x - dir[0], y - dir[1], cur[2] + cnt});
            }
        }
        return res;
    }
}
