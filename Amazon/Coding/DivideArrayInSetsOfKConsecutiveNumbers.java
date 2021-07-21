package Amazon.Coding;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class DivideArrayInSetsOfKConsecutiveNumbers {
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>();// min heap
        for (int i : map.keySet()) {//O(MlogM)
            pq.offer(i);
        }
        while (pq.size() > 0) {//O(M)
            int cur = pq.poll();
            int cnt = map.get(cur);
            if (cnt <= 0) {
                continue;
            }
            for (int i = 0; i < k; i++) {// O(k)
                if (map.getOrDefault(cur + i, 0) < cnt) {
                    return false;
                }
                map.put(cur + i, map.get(cur + i) - cnt);
            }
        }
        return true;
    }
    // time: O(MlogM + Mk) space: O(M)
}
