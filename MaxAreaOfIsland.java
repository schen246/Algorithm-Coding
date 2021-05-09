public class MaxAreaOfIsland {
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = grid.length, n = grid[0].length, res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, bfs(grid, i, j));
                }
            }
        }
        return res;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int bfs(int[][] grid, int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{i, j});
        grid[i][j] = 0;
        int res = 1;
        while (q.size() > 0) {
            int[] cur = q.poll();
            for (int[] dir : DIRS) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                    q.offer(new int[]{x, y});
                    grid[i][j] = 0;
                    res++;
                }
            }
        }
        return res;
    }
    // time: O(m * n) space: O(m * n)
}
