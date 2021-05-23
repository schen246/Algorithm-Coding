import java.util.Queue;

public class NumberOfDistinctIslands {
    public int numDistinctIslands(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        Set<String> res = new HashSet<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    StringBuilder sb = new StringBuilder();
                    dfs(grid, i, j, sb);// bfs(grid, i, j)
                    res.add(sb.toString());
                }
            }
        }
        return res.size();
    }

    private String bfs(int[][] grid, int i, int j) {
        Queue<int[]> q = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        q.offer(new int[]{i, j});
        grid[i][j] = 0;
        int[][] dir = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (q.size() > 0) {
            int[] cur = q.poll();
            for (int index = 0; index < 4; index++) {
                int x = cur[0] + dir[index][0], y = cur[1] + dir[index][1];
                if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                    sb.append(index);
                    q.offer(new int[]{x, y});
                    grid[x][y] = 0;
                }
            }
            sb.append(",");
        }
        return sb.toString();
    }
    // bfs time: O(m * n) space: O(m * n)

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void dfs(int[][] grid, int i, int j, StringBuilder sb) {
        grid[i][j] = 0;
        for (int index = 0; index < 4; index++) {
            int x = i + DIRS[index][0], y = j + DIRS[index][1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1) {
                sb.append(index);
                dfs(grid, x, y, sb);
                sb.append(",");
            }
        }
    }
    // dfs time: O(m * n) space: O(m * n)
}
