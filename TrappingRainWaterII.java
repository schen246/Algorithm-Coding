import java.util.PriorityQueue;

public class TrappingRainWaterII {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int trapRainWater(int[][] heightMap) {
        if (heightMap.length < 1) {
            return 0;
        }
        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (a[2] - b[2]));//x,y,h
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    pq.offer(new int[]{i, j, heightMap[i][j]});
                    visited[i][j] = true;
                }
            }
        }
        int res = 0;
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            for (int[] dir : DIRS) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                if (x >= 0 && x < m && y >= 0 && y < 0 && !visited[x][y]) {
                    if (cur[2] > heightMap[x][y]) {
                        res += (cur[2] - heightMap[x][y]);
                    }
                    pq.offer(new int[]{x, y, Math.max(cur[2], heightMap[x][y])});
                    visited[x][y] = true;
                }
            }
        }
        return res;
    }
    // time: O(m * n log(m * n)) space: O(m * n)
}
