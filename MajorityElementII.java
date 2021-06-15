import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MajorityElementII {
    // M1: map - time: O(n) space: O(n)
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();// num, freq
        Set<Integer> res = new HashSet<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > nums.length / 3) {
                res.add(num);
            }
        }
        return new ArrayList<>(res);
    }
    
    // M2: game theory - time: O(n) space: O(1)
    public List<Integer> majorityElement2(int[] nums) {
        List<Integer> res = new ArrayList<>();
        int candidate1 = nums[0], candidate2 = nums[0];
        int cnt1 = 0, cnt2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                cnt1++;
            } else if (num == candidate2) {
                cnt2++;
            } else if (cnt1 == 0) {
                candidate1 = num;
            } else if (cnt2 == 0) {
                candidate2 = num;
            } else {
                cnt1--;
                cnt2--;
            }
        }
        cnt1 = 0;
        cnt2 = 0;
        for (int num : nums) {
            if (num == candidate1) {
                cnt1++;
            } else if (num == candidate2) {
                cnt2++;
            }
        }
        if (cnt1 > nums.length / 3) {
            res.add(candidate1);
        }
        if (cnt2 > nums.length / 3) {
            res.add(candidate2);
        }
        return res;
    }
}
