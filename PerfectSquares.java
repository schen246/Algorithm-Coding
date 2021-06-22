import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class PerfectSquares {
    // M1: bfs - time: O(sqrt(n)^h) space: O(sqrt(n)^h)
    public int numSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }
        if (n == 1) {
            return 1;
        }
        Queue<Integer> q = new ArrayDeque<>();// cur sum
        Set<Integer> set = new HashSet<>();
        q.offer(0);
        set.add(0);
        int steps = 0;
        while (q.size() > 0) {
            int size = q.size();
            steps++;
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                for (int next : squares) {
                    if (cur + next == n) {
                        return steps;
                    }
                    if (set.add(cur + next)) {
                        q.offer(cur + next);
                    }
                }
            }
        }
        return -1;
    }

    // M2: dp - time: O(n * sqrt(n)) space: O(n)
    public int numSquares2(int n) {
        int[] dp = new int[n + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
