import java.util.ArrayDeque;
import java.util.Queue;

public class DesignHitCounter {
    private static final int LIMIT = 300;
    private Queue<Integer> q;
    public DesignHitCounter() {
        q = new ArrayDeque<>();
    }

    public void hit(int timestamp) {// O(1)
        q.offer(timestamp);
    }

    public int getHits(int timestamp) {// average O(1) if hit calls is small
        while (q.size() > 0 && timestamp - q.peek() >= LIMIT) {
            q.poll();
        }
        return q.size();
    }
}

class HitCounter {

    private int[] hits;
    private int[] times;
    private static final int LIMIT = 300;
    public HitCounter() {
        hits = new int[LIMIT];
        times = new int[LIMIT];
    }
    
    public void hit(int timestamp) {
        int index = timestamp % LIMIT;
        if (times[index] != timestamp) {
            times[index] = timestamp;
            hits[index] = 0;
        }
        hits[index]++;
    }
    
    public int getHits(int timestamp) {
        int res = 0;
        for (int i = 0; i < LIMIT; i++) {
            if (timestamp >= times[i] && timestamp - times[i] < LIMIT) {
                res += hits[i];
            }
        }
        return res;
    }
}