import java.util.Arrays;
import java.util.HashSet;

import javax.print.attribute.HashAttributeSet;

public class ThreeSum {
    // time: O(n^2) space: O(n) sort
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return res;
        }
        Arrays.sort(nums);
        for (int i = 0; i <= nums.length - 3; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                twoSum(i, nums, res);
            }
        }
        return res;
    }

    private void twoSum(int i, int[] nums, List<List<Integer>> res) {// time: O(n) space: O(1)
        int target = -nums[i];
        int l = i + 1, r = nums.length - 1;
        while (l < r) {
            if (nums[l] + nums[r] == target) {
                while (l + 1 < r && nums[l + 1] == nums[l]) {
                    l++;
                }
                if (r - 1 > l && nums[r - 1] == nums[r]) {
                    r--;
                }
                if (l >= r) {
                    break;
                }
                res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                l++;
                r--;
            } else if (nums[l] + nums[r] < target) {
                l++;
            } else {
                r--;
            }
        }
    }

    private void twoSum2(int i, int[] nums, List<List<Integer>> res) {// time: O(n) space: O(n)
        int target = -nums[i];
        Set<Integer> set = new HashSet<>();
        for (int j = i + 1; j < nums.length; j++) {
            if (set.contains(target - nums[j])) {
                res.add(Arrays.asList(nums[i], target - nums[j], nums[j]));
                while (j + 1 < nums.length && nums[j] == nums[j + 1]) {
                    j++;
                }
            }
            set.add(nums[j]);
        }
    }
}
