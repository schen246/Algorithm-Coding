public class PathWithMaximumMinimumValue {
    // M1: binary search + dfs + pruning - time: O(log(A[0][0]) * mn) space: O(mn)
    public int maximumMinimumPath(int[][] A) {
        int left = 0, right = A[0][0];
        int res = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canReach(A, mid)) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return res;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean canReach(int[][] A, int score) {
        boolean[][] visited = new boolean[A.length][A[0].length];
        return dfs(A, 0, 0, score, visited);
    }

    private boolean dfs(int[][] A, int i, int j, int score, boolean[][] visited) {
        if (A[i][j] < score) {
            return false;
        }
        if (i == A.length - 1 && j == A[0].length - 1) {
            return true;
        }
        visited[i][j] = true;
        for (int[] dir : DIRS) {
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < A.length && y >= 0 && y < A[0].length && !visited[x][y]) {
                if (dfs(A, x, y, score, visited)) {
                    return true;
                }
            }
        }
        return false;
    }
}
