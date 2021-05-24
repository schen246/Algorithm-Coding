public class ShortestPathInBinaryMatrix {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};

    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<int[]> q = new ArrayDeque<>();
        if (grid[0][0] == 1) {
            return -1;
        }
        q.offer(new int[]{0, 0});
        grid[0][0] = 1;
        int steps = 0, n = grid.length;
        while (q.size() > 0) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < n && y >= 0 && y < n && grid[x][y] == 0) {
                        if (x == n - 1 && y == n - 1) {
                            return steps + 1;
                        }
                        q.offer(new int[]{x, y});
                        grid[x][y] = 1;
                    }
                }
            }
        }
        return -1;
    }
    // time: O(m * n) space: O(m * n)
}
