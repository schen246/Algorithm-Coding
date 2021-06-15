import java.util.HashMap;
import java.util.Map;

public class LRU {
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



class LRUCache2 {
    class Node {
        int key, val;
        Node prev, next;
        public Node(int k, int v) {
            key = k;
            val = v;
        }
    }
    
    Node head, tail;
    int capacity;
    Map<Integer, Node> map;
    public LRUCache2(int capacity) {
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
        this.capacity = capacity;
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (map.get(key) == null) {
            return -1;
        }
        Node node = map.get(key);
        // disconnect node with prev and next
        node.prev.next = node.next;
        node.next.prev = node.prev;
        // connect node with tail.prev
        tail.prev.next = node;
        node.prev = tail.prev;
        // connect node with tail
        node.next = tail;
        tail.prev = node;
        return node.val;
    }
    
    public void put(int key, int value) {
        if (map.get(key) != null) {
            map.get(key).val = value;
            get(key);
            return;
        }
        Node node = new Node(key, value);
        map.put(key, node);
        // add node to tail
        tail.prev.next = node;
        node.prev = tail.prev;
        node.next = tail;
        tail.prev = node;
        if (map.size() > capacity) {
            Node cur = head.next;
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            map.remove(cur.key);
        }
    }
}