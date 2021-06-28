import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class HighFive {
    // map + min heap - time: O(nlogn) space: O(n)
    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] item : items) {// O(nlog5)
            map.putIfAbsent(item[0], new PriorityQueue<>());// min heap
            PriorityQueue<Integer> cur = map.get(item[0]);
            cur.offer(item[1]);
            if (cur.size() > 5) {
                cur.poll();
            }
        }
        List<int[]> res = new ArrayList<>();
        for (int id : map.keySet()) {// O(ids * log5) = O(nlog5)
            PriorityQueue<Integer> pq = map.get(id);
            int temp = 0;
            while (pq.size() > 0) {
                temp += pq.poll();
            }
            res.add(new int[]{id, temp / 5});
        }
        Collections.sort(res, (a, b) -> a[0] - b[0]);// O(ids log ids) = O(nlogn)
        return res.toArray(new int[res.size()][]);
    }
}
