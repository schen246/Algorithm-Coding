import java.util.HashMap;
import java.util.Map;

public class MajorityElement {
    // M1: sort - time: O(nlogn) space: O(n)
    // M2: map - time: O(n) space: O(n)
    public int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
            if (map.get(num) > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }
    // M3: game theory - time: O(n) space: O(1)
    // count majority element +1, and others -1
    public int majorityElement2(int[] nums) {
        int cnt = 0;
        Integer cur = null;
        for (int num : nums) {
            if (cnt == 0) {
                cur = num;
            }
            if (num == cur) {
                cnt++;
            } else {
                cnt--;
            }
        }
        return cur;
    }

}
