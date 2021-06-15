import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IntersectionOfTwoArraysII {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int e : nums1) {
            map.put(e, map.getOrDefault(e, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        for (int e : nums2) {
            Integer n = map.get(e);
            if (n != null) {
                res.add(e);
                if (n == 1) {
                    map.remove(e);
                } else {
                    map.put(e, n - 1);
                }
            }
        }
        int[] ans = new int[res.size()];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
    // time: O(m +n) space: O(min(m, n))
    // F1: sorted -> two pointers O(1) space
    // F2: nums2 stored on disk, memory limited -> external sort + two pointers
}
