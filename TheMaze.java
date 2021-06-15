import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class TheMaze {
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        if (maze == null || maze.length == 0 || maze[0].length == 0 || maze[start[0]][start[1]] == 1) {
            return false;
        }
        if (start[0] == destination[0] && start[1] == destination[1]) {
            return true;
        }
        Queue<int[]> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        set.add(start[0] + ":" + start[1]);
        q.offer(new int[]{start[0], start[1]});
        while (q.size() > 0) {
            int[] cur = q.poll();
            for (int[] nei : getNeighbors(maze, cur)) {
                if (nei[0] == destination[0] && nei[1] == destination[1]) return true;
                if (set.add(nei[0] + ":" + nei[1])) {
                    q.offer(new int[]{nei[0], nei[1]});
                }
            }
        }
        return false;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private List<int[]> getNeighbors(int[][] maze, int[] cur) {
        List<int[]> res = new ArrayList<>();
        for (int[] dir : DIRS) {
            int x = cur[0] + dir[0], y = cur[1] + dir[1];
            while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] == 0) {
                x += dir[0];
                y += dir[1];
            }
            if (x - dir[0] != cur[0] || y - dir[1] != cur[1]) {
                res.add(new int[]{x - dir[0], y - dir[1]});
            }
        }
        return res;
    }
    // time: O(m * n) space: O(m * n)
}
