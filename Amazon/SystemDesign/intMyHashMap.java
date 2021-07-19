package Amazon.SystemDesign;

public class intMyHashMap {
    class Node {
        int key, val;
        Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    private int size;
    private double loadFactor;
    private Node[] array;
    
    public intMyHashMap() {
        this.size = 0;
        this.loadFactor = 0.75;
        array = new Node[256];
    }
    
    public void put(int key, int value) {
        int index = hash(key);
        Node head = array[index];
        Node node = head;
        // case 1: exist
        while (node != null) {
            if (node.key == key) {
                node.val = value;
                return;
            }
            node = node.next;
        }
        // case 2: not exist
        Node newNode = new Node(key, value);
        newNode.next = head;
        array[index] = newNode;
        size++;
        if ((double)size / array.length >= loadFactor) {
            rehash();
        }
    }
    
    private void rehash() {
        Node[] oldArray = array;
        array = new Node[array.length * 2];
        for (Node cur : oldArray) {
            while (cur != null) {
                Node next = cur.next;
                int index = hash(cur.key);
                cur.next = array[index];
                array[index] = cur;
                cur = next;
            }
        }
    }
    
    private int hash(int key) {
        int hashNumber = Integer.hashCode(key);
        return (hashNumber & 0X7FFFFFFF) % array.length;
    }
    
    public int get(int key) {
        int index = hash(key);
        Node cur = array[index];
        while (cur != null) {
            if (cur.key == key) {
                return cur.val;
            }
            cur = cur.next;
        }
        return -1;
    }
    
    public void remove(int key) {
        int index = hash(key);
        Node cur = array[index];
        Node pre = null;
        while (cur != null) {
            if (cur.key == key) {
                if (pre == null) {
                    array[index] = cur.next;
                } else {
                    pre.next = cur.next;
                }
                size--;
                return;
            }
            pre = cur;
            cur = cur.next;
        }
    }
}
