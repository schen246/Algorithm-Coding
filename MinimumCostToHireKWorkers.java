import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumCostToHireKWorkers {
    // time: O(nlogn) + O(nlogk) space: O(n) + O(k)
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        List<int[]> list = new ArrayList<>();
        for (int i = 0; i < quality.length; i++) {
            list.add(new int[]{quality[i], wage[i]});
        }
        Collections.sort(list, (a, b) -> {
            double ratio_a = (double)a[1] / a[0];
            double ratio_b = (double)b[1] / b[0];
            return Double.compare(ratio_a, ratio_b);// small ratio first
        });
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        double res = Double.MAX_VALUE;
        int totalQuality = 0;
        for (int[] cur : list) {
            totalQuality += cur[0];
            pq.offer(cur[0]);
            if (pq.size() > K) {
                totalQuality -= pq.poll();
            }
            if (pq.size() == K) {
                res = Math.min(res, (double)cur[1] / cur[0] * totalQuality);
            }
        }
        return res;
    }
}
