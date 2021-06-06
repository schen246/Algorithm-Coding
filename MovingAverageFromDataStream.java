import java.util.Queue;

public class MovingAverageFromDataStream {
    Queue<Integer> q;
    int size;
    int sum;
    public MovingAverageFromDataStream(int size) {// O(1)
        q = new ArrayDeque<>();
        this.size = size;
        sum = 0;
    }

    public double next(int val) {// O(1)
        sum += val;
        q.offer(val);
        if (q.size() > size) {
            sum -= q.poll();
        }
        return (double)sum / q.size();
    }
}
