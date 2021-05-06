public class IntersectionOfTwoArrays {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null) {
            return null;
        }
        if (nums1.length > nums2.length) {
            return intersection(nums2, nums1);
        }
        Set<Integer> set = new HashSet<>();// O(min(m, n)) space
        for (int num : nums1) {
            set.add(num);
        }
        Set<Integer> res = new HashSet<>();
        for (int e : nums2) {
            if (set.contains(e)) {
                res.add(e);
            }
        }
        if (res.isEmpty()) {
            return new int[0];
        }
        int[] ans = new int[res.size()];
        int i = 0;
        for (int e : res) {
            ans[i++] = e;
        }
        return ans;
    }
    // time: O(m + n) space: O(min(m, n))
}
