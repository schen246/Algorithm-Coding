package Amazon.Coding;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class ShortestPathtoGetFood {
    // bfs + inplace dedup - time: O(mn) space: O(mn)
    public int getFood(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') {
                    grid[i][j] = 'X';
                    q.offer(new int[]{i, j});
                }
            }
        }
        int steps = 0;
        while (q.size() > 0) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 'X') {
                        if (grid[x][y] == '#') {
                            return steps;
                        }
                        grid[x][y] = 'X';
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return -1;
    }

    // bfs + set dedup - time: O(mn) space: O(mn)
    public int getFood2(char[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '*') {
                    q.offer(new int[]{i, j});
                    set.add(i * n + j);
                }
            }
        }
        int steps = 0;
        while (q.size() > 0) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] != 'X') {
                        if (grid[x][y] == '#') {
                            return steps;
                        }
                        if (set.add(x * n + y)) {
                            q.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
}
