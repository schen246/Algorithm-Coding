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

    // M2: binary search - time: O(nlogD) space: O(1)
    public double minmaxGasDist2(int[] stations, int k) {
        double left = 0.0, right = Double.MIN_VALUE;
        for (int i = 1; i < stations.length; i++) {
            right = Math.max(right, (double)stations[i] - stations[i - 1]);
        }
        double res = 0.0;
        while (left + 1e-6 <= right) {// O(log(max interval))
            double mid = left + (right - left) / 2;
            if (canAdd(stations, k, mid)) {
                res = mid;
                right = mid;
            } else {
                left = mid;
            }
        }
        return res;
    }

    private boolean canAdd(int[] stations, int k, double penalty) {// O(n)
        int cnt = 0;
        for (int i = 1; i < stations.length; i++) {
            cnt += Math.ceil((stations[i] - stations[i - 1]) / penalty) - 1;
        }
        return cnt <= k;
    }
}
