import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle {
    // bfs - time: O(2^6 * 6) space: O(2^6)
    public int slidingPuzzle(int[][] board) {
        int m = board.length, n = board[0].length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(board[i][j]);
            }
        }
        String source = sb.toString();
        String target = "123450";
        Queue<String> q = new ArrayDeque<>();
        Set<String> set = new HashSet<>();
        q.offer(source);
        int steps = 0;
        while (q.size() > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return steps;
                }
                if (!set.add(cur)) {
                    continue;
                }
                for (String nei : getNei(cur)) {
                    if (!set.contains(nei)) {
                        q.offer(nei);
                    }
                }
            }
            steps++;
        }
        return -1;
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private List<String> getNei(String cur) {
        List<String> res = new ArrayList<>();
        int index = cur.indexOf('0');// O(6)
        int i = index / 3, j = index % 3;
        for (int[] dir : DIRS) {// O(4)
            int x = i + dir[0], y = j + dir[1];
            if (x >= 0 && x < 2 && y >= 0 && y < 3) {
                char[] arr = cur.toCharArray();// O(6)
                arr[index] = arr[x * 3 + y];
                arr[x * 3 + y] = '0';
                res.add(new String(arr));// O(6)
            }
        }
        return res;
    }
}
