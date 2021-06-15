import java.util.PriorityQueue;

public class KthLargestInAStream {

    PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
    int k = 0;

    public KthLargestInAStream(int k, int[] nums) {
        this.k = k;
        for (int i = 0; i < nums.length; i++) {
            pq.offer(nums[i]);
            if (pq.size() > k) pq.poll();
        }
    }

    public int add(int val) {
        pq.offer(val);
        if (pq.size() > k) pq.poll();
        return pq.peek();
    }
}
