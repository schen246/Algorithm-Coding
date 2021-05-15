public class NumberOfEnclaves {
    public int numEnclaves(int[][] A) {
        int m = A.length, n = A[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
                    if (A[i][j] == 1) {
                        dfs(A, i, j);
                    }
                }
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (A[i][j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private void dfs(int[][] A, int i, int j) {
        if (i < 0 || i >= A.length || j < 0 || j >= A[0].length) {
            return;
        }
        if (A[i][j] == 0) {
            return;
        }
        A[i][j] = 0;
        for (int[] dir : DIRS) {
            dfs(A, i + dir[0], j + dir[1]);
        }
    }
    // time: O(m * n) space: O(m * n)
}
