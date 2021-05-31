import java.util.TreeSet;

public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualToLimit {
    // // TreeSet - time: O(nlogn) space: O(n)
    public int longestSubarray(int[] nums, int limit) {
        TreeSet<Integer> minHeap = new TreeSet<>((i, j) -> {
            return nums[i] == nums[j] ? i - j : nums[i] - nums[j];
        });
        TreeSet<Integer> maxHeap = new TreeSet<>((i, j) -> {
            return nums[i] == nums[j] ? i - j : nums[j] - nums[i];
        });
        int i = 0, j = 0, res = 0;
        while (j < nums.length) {// time: O(nlogn) space: O(n)
            // add j
            minHeap.add(j);
            maxHeap.add(j);
            if (nums[maxHeap.first()] - nums[minHeap.first()] <= limit) {
                res = Math.max(res, j - i + 1);
                j++;
            } else {
                // remove i
                minHeap.remove(i);
                maxHeap.remove(i);
                i++;
            }
        }
        return res;
    }
}
