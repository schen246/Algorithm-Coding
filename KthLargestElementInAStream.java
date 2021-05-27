import java.util.PriorityQueue;

public class KthLargestElementInAStream {
    int k;
    PriorityQueue<Integer> pq;// min heap

    public KthLargestElementInAStream(int k, int[] nums) {// time: O(nlogk) space: O(k)
        this.k = k;
        for (int num : nums) {
            pq.offer(num);
            if (pq.size() > k) {
                pq.poll();
            }
        }
    }

    public int add(int val) {//time: logk
        pq.offer(val);
        if (pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }

}
