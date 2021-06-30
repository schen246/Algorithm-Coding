package Amazon;

import java.util.HashMap;
import java.util.Map;

public class FourSumII {
    // M1: map - time: O(n^2) space: O(n^2)
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                map.put(a + b, map.getOrDefault(a + b, 0) + 1);
            }
        }
        int res = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                res += map.getOrDefault(-(c + d), 0);
            }
        }
        return res;
    }

    // M2: kSum - time: O(n^(k/2)) space: O(n^k/2)
    public int fourSumCount2(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        return kSum(new int[][]{nums1, nums2, nums3, nums4});
    }

    private int kSum(int[][] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        addMap(nums, map, 0, 0);// O(n^(k/2))
        int[] res = new int[1];
        checkAnother(nums, map, nums.length / 2, 0, res);// O(n^(k/2))
        return res[0];
    }

    // recursion - divide and conquer
    private void checkAnother(int[][] nums, Map<Integer, Integer> map, int i, int sum, int[] res) {
        if (i == nums.length) {
            res[0] += map.getOrDefault(sum, 0);
            return;
        }
        for (int a : nums[i]) {
            checkAnother(nums, map, i + 1, sum - a, res);
        }
    }

    private void addMap(int[][] nums, Map<Integer, Integer> map, int i, int sum) {
        if (i == nums.length / 2) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return;
        }
        for (int a : nums[i]) {
            addMap(nums, map, i + 1, sum + a);
        }
    }
}
