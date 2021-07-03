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
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        return kSum(new int[][]{A, B, C, D});
    }
    
    private int kSum(int[][] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        addMap(0, 0, nums, map);
        int[] res = new int[1];
        checkAnother(nums.length / 2, 0, nums, map, res);
        return res[0];
    }
    
    // dfs - all pairs of another sum
    private void checkAnother(int index, int sum, int[][] nums, Map<Integer, Integer> map, int[] res) {
        if (index == nums.length) {
            res[0] += map.getOrDefault(sum, 0);
            return;
        }
        for (int a : nums[index]) {
            checkAnother(index + 1, sum - a, nums, map, res);
        }
    }
    
    // dfs - all pairs of sum
    private void addMap(int index, int sum, int[][] nums, Map<Integer, Integer> map) {
        if (index == nums.length / 2) {
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            return;
        }
        for (int a : nums[index]) {
            addMap(index + 1, sum + a, nums, map);
        }
    }
}
