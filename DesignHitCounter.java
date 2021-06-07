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