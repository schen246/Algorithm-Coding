public class _01Matrix {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return matrix;
        }
        Queue<int[]> q = new ArrayDeque<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    q.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        int depth = 0;
        while (q.size() > 0) {
            int size = q.size();
            depth++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && matrix[x][y] == Integer.MAX_VALUE) {
                        q.offer(new int[]{x, y});
                        matrix[x][y] = depth;
                    }
                }
            }
        }
        return matrix;
    }
    // time: O(m*n) space: O(m*n)
}
