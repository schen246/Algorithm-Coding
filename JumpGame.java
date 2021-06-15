import java.util.ArrayDeque;
import java.util.Queue;

public class JumpGame {
    // M1: dp - time: O(n^2) space: O(n)
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] dp = new boolean[n];
        dp[n - 1] = true;
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i + 1; j <= Math.min(i + nums[i], n - 1); j++) {
                if (dp[j]) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[0];
    }

    // M2: recursion + memo - time: O(n) space: O(n)
    public boolean canJump2(int[] nums) {
        int[] visited = new int[nums.length];
        return helper(nums, 0, visited);
    }
    
    private boolean helper(int[] nums, int index, int[] visited) {
        if (visited[index] != 0) {
            return visited[index] == 1;
        }
        if (index == nums.length - 1) {
            visited[index] = 1;
            return true;
        }
        for (int i = index + 1; i <= Math.min(nums.length - 1, index + nums[index]); i++) {
            if (helper(nums, i, visited)) {
                visited[index] = 1;
                return true;
            }
        }
        visited[index] = -1;
        return false;
    }

    // M3: queue - time: O(n) space: O(n)
    public boolean canJump3(int[] nums) {
        int n = nums.length;
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        visited[0] = true;
        while (q.size() > 0) {
            int cur = q.poll();
            for (int i = cur + nums[cur]; i > cur; i--) {
                if (i >= n - 1) {
                    return true;
                }
                if (!visited[i]) {
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
        return false;
    }

    // M4: greedy - time: O(n) space: O(1)
    public boolean canJump4(int[] nums) {
        int pos = nums.length - 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            if (i + nums[i] >= pos) {
                pos = i;
            }
        }
        return pos == 0;
    }
}
