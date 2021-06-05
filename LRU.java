import java.util.HashMap;
import java.util.Map;

public class LRU {
    Node head;
    Node tail;
    int capacity;
    Map<Integer, Node> map;

    public LRU(int capacity) {// assume capacity >= 1
        this.capacity = capacity;
        head = new Node(-1, -1);
        tail = head;
        map = new HashMap<>();
    }

    public int get(int key) {// O(1)
        if (!map.containsKey(key)) {
            return -1;
        }
        Node cur = map.get(key);
        if (cur != tail) {
            // disconnect cur with prev and next
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            // connect cur with tail
            cur.prev = tail;
            cur.prev.next = cur;
            cur.next = null;
            // update tail with cur
            tail = cur;
        }
        return cur.val;
    }

    public void put(int key, int value) {// O(1)
        if (get(key) != -1) {
            map.get(key).val = value;
            return;
        }
        Node cur = new Node(key, value);
        map.put(key, cur);
        cur.prev = tail;
        cur.prev.next = cur;
        tail = cur;
        if (map.size() > capacity) {
            int k = head.next.key;
            map.remove(k);
            head.next = head.next.next;
            head.next.prev = head;
        }
    }
}

class Node {
    int key;
    int val;
    Node prev;
    Node next;

    public Node(int key, int val) {
        this.key = key;
        this.val = val;
    }
}
