package Amazon.Design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class DesignSnakeGame {
    int m, n;
    Deque<int[]> q = new ArrayDeque<>();
    Set<Integer> set = new HashSet<>();
    int[][] matrix;
    int cnt;
    Map<String, int[]> map = new HashMap<>();
    {
        map.put("L", new int[]{0, -1});
        map.put("R", new int[]{0, 1});
        map.put("U", new int[]{-1, 0});
        map.put("D", new int[]{1, 0});
    }

    public DesignSnakeGame(int width, int height, int[][] food) {
        m = height;
        n = width;
        q.offerFirst(new int[]{0, 0});
        set.add(0 * n + 0);
        matrix = food;
        cnt = 0;
    }

    public int move(String direction) {
        int[] dir = map.get(direction);
        int[] cur = q.peekFirst();
        int x = cur[0] + dir[0], y = cur[1] + dir[1];
        // remove tail
        int[] tail = q.pollLast();
        set.remove(tail[0] * n + tail[1]);
        // check out bound or eat body
        if (x < 0 || x >= m || y < 0 || y >= n || set.contains(x * n + y)) {
            return -1;
        }
        // add head
        q.offerFirst(new int[]{x, y});
        set.add(x * n + y);
        if (cnt < matrix.length && matrix[cnt][0] == x && matrix[cnt][1] == y) {
            // if food
            q.offerLast(tail);
            set.add(tail[0] * n + tail[1]);
            cnt++;
        }
        return cnt;
    }
}
