import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowMaximum {
    // M1: maxHeap O(nlogk)
    // M2: sliding window O(n) with non-increasing inside
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            return new int[0];
        }
        int[] res = new int[nums.length - k + 1];
        Deque<Integer> dq = new ArrayDeque<>();
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            while (!dq.isEmpty() && dq.peekFirst() < nums[j]) {
                dq.pollFirst();
            }
            // add j
            dq.offerFirst(nums[j]);
            if (j - i + 1 == k) {
                // remove i
                int cur = dq.peekLast();
                if (cur == nums[i]) {
                    dq.pollLast();
                }
                res[i] = cur;
                i++;
            }
        }
        return res;
    }
}
