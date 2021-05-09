public class WallsAndGates {
    // time: O(m * n) space: O(m * n)
    public void wallsAndGates(int[][] rooms) {
        if (rooms == null) {
            return;
        }
        int m = rooms.length, n = rooms[0].length;
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (rooms[i][j] == 0) {
                    q.offer(new int[]{i, j});
                }
            }
        }
        int dist = 0;
        while (q.size() > 0) {
            int size = q.size();
            dist++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1];
                    if (x >= 0 && x < m && y >= 0 && y < n && rooms[x][y] == Integer.MAX_VALUE) {
                        q.offer(new int[]{x, y});
                        rooms[x][y] = dist;
                    }
                }
            }
        }
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
}
