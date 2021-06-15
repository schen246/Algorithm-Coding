import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class ShortestPathToGetAllKeys {
    public int shortestPathAllKeys(String[] grid) {
        // assume: matrix, only .#a-zA-Z, each char at most once, <a,A> -> <key, lock>
        // bfs + bitMask state
        // time: O(m * n * 2^k) space: O(m * n)
        int m = grid.length, n = grid[0].length();
        Queue<int[]> q = new ArrayDeque<>();//x,y,state
        Set<String> visited = new HashSet<>();
        int target = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char c = grid[i].charAt(j);
                if (c == '@') {
                    q.offer(new int[]{i, j, 0});
                    visited.add(i + ":" + j + "" + 0);
                } else if (c >= 'a' && j <= 'z') {
                    target |= (1 << (c - 'a'));
                }
            }
        }
        if (target == 0) return 0;
        int depth = 0;
        while (q.size() > 0) {
            int size = q.size();
            depth++;
            for (int i = 0; i < size; i++) {
                int[] cur = q.poll();
                for (int[] dir : DIRS) {
                    int x = cur[0] + dir[0], y = cur[1] + dir[1], key = cur[2];
                    if (x >= 0 && x < m && y >= 0 && y < n) {
                        char c = grid[x].charAt(y);
                        if (c == '#') continue;
                        if (c >= 'A' && c <= 'Z' && (key & (1 << (c - 'A'))) == 0) continue;
                        if (c >= 'a' && c <= 'z') {
                            key |= (1 << (c - 'a'));
                        }
                        if (visited.add(x + ":" + y + ":" + key)) {
                            if (key == target) return depth;
                            q.offer(new int[]{x, y, key});
                        }
                    }
                }
            }
        }
        return -1;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
}
