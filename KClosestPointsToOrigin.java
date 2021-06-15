import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class KClosestPointsToOrigin {
    // M1: pq -> time: O(nlogk) space: O(k)
    public List<List<Integer>> kClosest(int[][] points, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return b[0] * b[0] + b[1] * b[1] - (a[0] * a[0] + a[1] * a[1]);
        });
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > K) {
                pq.poll();
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            List<Integer> ans = new ArrayList<>();
            ans.add(cur[0]);
            ans.add(cur[1]);
            res.add(ans);
        }
        return res;
    }

    // M2: quick select -> time: average O(n) space: O(1)
    public List<List<Integer>> kClosest2(int[][] points, int K) {
        Point[] p = new Point[points.length];
        for (int i = 0; i < points.length; i++) {
            p[i] = new Point(points[i]);
        }
        quickSelect(p, 0, points.length - 1, K - 1);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            List<Integer> cur = new ArrayList<>();
            cur.add(p[i].x);
            cur.add(p[i].y);
            res.add(cur);
        }
        return res;
    }
    
    private void quickSelect(Point[] p, int left, int right, int k) {
        while (left <= right) {
            int mid = partition(p, left, right);
            if (mid == k) {
                return;
            }
            if (mid < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }    
        }
    }
    
    private int partition(Point[] p, int left, int right) {
        int val = p[left].d;
        int i = left + 1, j = right;
        while (i <= j) {
            while (i <= j && p[i].d <= val) {
                i++;
            }
            while (i <= j && p[j].d > val) {
                j--;
            }
            if (i > j) {
                break;
            }
            swap(p, i++, j--);
        }
        swap(p, left, j);
        return j;
    }
    
    private void swap(Point[] p, int i, int j) {
        Point tmp = p[i];
        p[i] = p[j];
        p[j] = tmp;
    }
}

class Point {
    int x;
    int y;
    int d;
    public Point(int[] a) {
        this.x = a[0];
        this.y = a[1];
        this.d = a[0] * a[0] + a[1] * a[1];
    }
}