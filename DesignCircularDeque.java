public class DesignCircularDeque {
    //   _     1      2     _
    // start         end
    int[] dq;
    int start;
    int end;
    public DesignCircularDeque(int k) {
        dq = new int[k + 1];
        start = 0;
        end = 0;
    }
    
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }
        dq[start] = value;
        start--;
        start = (start + dq.length) % dq.length;
        return true;
    }
    
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }
        end++;
        end %= dq.length;
        dq[end] = value;
        return true;
    }
    
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }
        start++;
        start %= dq.length;
        return true;
    }
    
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }
        end--;
        end = (end + dq.length) % dq.length;
        return true;
    }
    
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }
        return dq[(start + 1) % dq.length];
    }
    
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }
        return dq[end];
    }
    
    public boolean isEmpty() {
        return start == end;
    }
    
    public boolean isFull() {
        return (end + 1) % dq.length == start;
    }
}
