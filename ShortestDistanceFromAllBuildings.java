public class ShortestDistanceFromAllBuildings {
    public int shortestDistance(int[][] grid) {
        // assume: at lease one building 1, if no res return -1, grid not null/empty, only 0/1/2
        int m = grid.length, n = grid[0].length, cnt = 0;
        List<int[]> ones = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    cnt++;
                    ones.add(new int[]{i, j});
                }
            }
        }
        int[][] count = new int[m][n];
        int[][] sum = new int[m][n];
        int res = Integer.MAX_VALUE;
        for (int[] one : ones) {
            Queue<int[]> q = new ArrayDeque<>();
            boolean[][] visited = new boolean[m][n];
            q.offer(one);
            visited[one[0]][one[1]] = true;
            int depth = 0;
            while (q.size() > 0) {
                int size = q.size();
                depth++;
                for (int i = 0; i < size; i++) {
                    int[] cur = q.poll();
                    for (int[] dir : DIRS) {
                        int x = cur[0] + dir[0], y = cur[1] + dir[1];
                        if (x >= 0 && x < m && y >= 0 && y < n && grid[x][y] == 1 && !visited[x][y]) {
                            q.offer(new int[]{x, y});
                            visited[x][y] = true;
                            count[x][y]++;
                            sum[x][y] += depth;
                            if (count[x][y] = cnt) {
                                res = Math.min(res, sum[x][y]);
                            }
                        }
                    }
                }
            }
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
    // time: O(m * n) space: O(m * n)
}
