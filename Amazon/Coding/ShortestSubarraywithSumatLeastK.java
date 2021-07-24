package Amazon.Coding;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarraywithSumatLeastK {
    // mono deque - time: O(n) space: O(n)
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        int[] sum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            sum[i] = sum[i - 1] + nums[i - 1];
        }
        Deque<Integer> dq = new ArrayDeque<>();// index with sum[index] keep increaring
        int res = Integer.MAX_VALUE;
        for (int i = 0; i <= n; i++) {
            while (dq.size() > 0 && sum[i] - sum[dq.peekLast()] >= k) {
                res = Math.min(res, i - dq.pollLast());
            }
            while (dq.size() > 0 && sum[i] <= sum[dq.peekFirst()]) {
                dq.pollFirst();
            }
            dq.offerFirst(i);
        }
        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
