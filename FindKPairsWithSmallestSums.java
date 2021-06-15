import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class FindKPairsWithSmallestSums {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<List<Integer>> res = new ArrayList<>();
        int m = nums1.length, n = nums2.length;
        if (m == 0 || n == 0) {
            return res;
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if ((nums1[a[0]] + nums2[a[1]]) == (nums1[b[0]] + nums2[b[1]])) {
                return nums1[a[0]] - nums1[b[0]];
            }
            return (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]);
        });
        Set<Integer> set = new HashSet<>();
        pq.offer(new int[]{0, 0});
        set.add(0 * n + 0);
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            List<Integer> ans = new ArrayList<>();
            ans.add(nums1[cur[0]]);
            ans.add(nums2[cur[1]]);
            res.add(ans);
            k--;
            if (k == 0) {
                break;
            }
            if (cur[0] + 1 < m && set.add((cur[0] + 1) * n + cur[1])) {
                pq.offer(new int[]{cur[0] + 1, cur[1]});
            }
            if (cur[1] + 1 < n && set.add(cur[0] * n + cur[1] + 1)) {
                pq.offer(new int[]{cur[0], cur[1] + 1});
            }
        }
        return res;
    }
    // time: O(klogk) space: O(min(m, n, k))
}
