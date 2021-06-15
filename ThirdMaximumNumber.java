import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

public class ThirdMaximumNumber {
    public int thirdMax(int[] nums) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) continue;
            pq.offer(num);
            if (pq.size() > 3) {
                pq.poll();
            }
        }
        if (pq.size() == 3) {
            return pq.peek();
        }
        while (pq.size() > 1) {
            pq.poll();
        }
        return pq.peek();
    }
    // time: O(nlog3) space: O(3)
}
