import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {
    // pq - time: nlogn space: O(n)
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int stick : sticks) {
            pq.offer(stick);
        }
        int res = 0;
        while (pq.size() >= 2) {
            int cur = pq.poll() + pq.poll();
            res += cur;
            pq.offer(cur);
        }
        return res;
    }
}
