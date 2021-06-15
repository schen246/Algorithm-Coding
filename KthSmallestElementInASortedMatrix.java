import java.util.PriorityQueue;

public class KthSmallestElementInASortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        // M1: PriorityQueue
        // time: O(klogk) space: O(k)
        int m = matrix.length;
        int n = matrix[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return matrix[a[0]][a[1]] - matrix[b[0]][b[1]];
        });
        pq.offer(new int[]{0, 0});
        int[] cur = null;
        int count = 1;
        while (!pq.isEmpty()) {
            cur = pq.poll();
            if (count == k) break;
            if (cur[0] + 1 < m) {
                pq.offer(new int[]{cur[0] + 1, cur[1]});
            }
            if (cur[0] == 0 && cur[1] + 1 < n) {
                pq.offer(new int[]{cur[0], cur[1] + 1});
            }
            count++;
        }
        return matrix[cur[0]][cur[1]];
    }
}
