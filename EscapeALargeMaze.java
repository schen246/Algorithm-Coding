import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class EscapeALargeMaze {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        Set<String> set = new HashSet<>();
        for (int[] b : blocked) {
            set.add(b[0] + ":" + b[1]);
        }
        return bfs(set, source, target) && bfs(set, target, source);
    }
    
    private boolean bfs(Set<String> set, int[] source, int[] target) {
        Queue<int[]> q = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        if (source[0] == target[0] && source[1] == target[1]) {
            return true;
        }
        q.offer(source);
        visited.add(source[0] + ":" + source[1]);
        while (q.size() > 0) {
            int[] cur = q.poll();
            for (int[] dir : DIRS) {
                int x = cur[0] + dir[0], y = cur[1] + dir[1];
                String s = x + ":" + y;
                if (x >= 0 && x < 1000000 && y >= 0 && y < 1000000 && !set.contains(s)) {
                    if (visited.add(s)) {
                        if (x == target[0] && y == target[1]) return true;
                        if (visited.size() > set.size() * set.size() / 2) return true;
                        q.offer(new int[]{x, y});
                    }
                }
            }
        }
        return false;
    }
    // bi-directional BFS 
    // time: O(m*n) space: O(m*n)
}
