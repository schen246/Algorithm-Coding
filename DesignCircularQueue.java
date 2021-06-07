public class DesignCircularQueue {
    //   _    1   2   3
    // start         end
    int[] q;
    int start;
    int end;

    public DesignCircularQueue(int k) {
        q = new int[k + 1];
        start = 0;
        end = 0;
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        end++;
        end %= q.length;
        q[end] = value;
        return true;
    }

    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        start++;
        start %= q.length;
        return true;
    }

    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        return q[(start + 1) % q.length];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return q[end];
    }

    public boolean isEmpty() {
        return start == end;
    }

    public boolean isFull() {
        return (end + 1) % q.length == start;
    }
}
