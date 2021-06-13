import java.util.PriorityQueue;

public class MinimizeMaxDistanceToGasStation {
    // M1: pq - time: O(n) + O(klogn) space: O(n)
    public double minmaxGasDist(int[] stations, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return Double.compare((double)a[0] / a[1], (double)b[0] / b[1]);
        });
        for (int i = 1; i < stations.length; i++) {
            pq.offer(new int[]{stations[i] - stations[i - 1], 1});
        }
        for (int i = 0; i < k; i++) {
            int[] cur = pq.poll();
            cur[1]++;
            pq.offer(cur);
        }
        return (double)pq.peek()[0] / pq.peek()[1];
    }
}
