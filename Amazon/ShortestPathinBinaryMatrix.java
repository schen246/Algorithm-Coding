public class ShortestPathinBinaryMatrix {
    // bfs - time: O(mn) space: O(mn)
    public int shortestPathBinaryMatrix(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        if (grid[0][0] == 1) {
            return -1;
        }
        Queue<int[]> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        if (0 == m - 1 && 0 == n - 1) {
            return 1;
        }
        q.offer(new int[]{0, 0});
        int steps = 0;
        while (q.size() > 0) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                if (!set.add(cur[0] * n + cur[1])) {
                    continue;
                }
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 0) {
                        if (x == m - 1 && y == n - 1) {
                            return steps + 1;
                        }
                        if (!set.contains(x * n + y)) {
                            q.offer(new int[]{x, y});
                        }
                    }
                }
            }
        }
        return -1;
    }
    
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {-1, -1}, {-1, 1}, {1, -1}, {1, 1}};
}
