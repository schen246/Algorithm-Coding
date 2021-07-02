import java.util.ArrayDeque;
import java.util.Queue;

public class RottingOranges {
    // M1: bfs - time: O(m * n) space: O(m * n)
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        int cnt = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cnt++;
                } else if (grid[i][j] == 2) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        if (cnt == 0) {
            return 0;
        }
        int minutes = 0;
        while (q.size() > 0) {
            int size = q.size();
            minutes++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1) {
                        q.offer(new int[]{x, y});
                        grid[x][y] = 2;
                        cnt--;
                    }
                }
            }
        }
        return cnt == 0 ? minutes - 1 : -1;
    }
}
