import java.util.HashMap;
import java.util.Map;

public class LFU {
    class Node {
        int key, val;
        int cnt;
        Node prev, next;
        public Node(int k, int v) {
            key = k;
            val = v;
            cnt = 1;
        }
    }

    class DLList {
        Node head, tail;
        int len;
        public DLList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
            len = 0;
        }

        public void addHead(Node node) {
            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;
            tail.prev = node;
            map.put(node.key, node);
            len++;
        }

        public void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            map.remove(node.key);
            len--;
        }

        private void removeTail() {
            remove(head.next);
        }
    }

    Map<Integer, Node> map;// key, node
    Map<Integer, DLList> freq;// cnt, double linked list
    int capacity;
    int maxFreq;

    public LFU(int capacity) {
        map = new HashMap<>();
        freq = new HashMap<>();
        this.capacity = capacity;
        maxFreq = 0;
    }

    public int get(int key) {// O(1)
        if (map.get(key) == null) {
            return -1;
        }
        Node node = map.get(key);
        int preCnt = node.cnt;
        DLList preList = freq.get(preCnt);
        preList.remove(node);
        node.cnt++;
        int curCnt = node.cnt;
        maxFreq = Math.max(maxFreq, curCnt);
        DLList curList = freq.getOrDefault(curCnt, new DLList());
        curList.addHead(node);
        freq.put(curCnt, curList);
        return node.val;
    }

    public void put(int key, int value) {// average O(1)
        if (capacity == 0) {
            return;
        }
        if (map.get(key) != null) {
            map.get(key).val = value;
            get(key);
            return;
        }
        Node node = new Node(key, value);
        DLList curList = freq.getOrDefault(1, new DLList());
        curList.addHead(node);
        if (map.size() > capacity) {
            if (curList.len > 1) {
                curList.removeTail();
            } else {
                for (int i = 2; i <= maxFreq; i++) {
                    if (freq.get(i) != null && freq.get(i).len > 0) {
                        freq.get(i).removeTail();
                        break;
                    }
                }
            }
        }
        freq.put(1, curList);
    }
}
