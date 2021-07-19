package Amazon.SystemDesign;

public class intMyHashSet {
    class Node {
        int key;
        Node next;
        public Node(int key) {
            this.key = key;
        }
    }

    int size;
    double loadFactor;
    Node[] array;
    
    public intMyHashSet() {
        size = 0;
        loadFactor = 0.75;
        array = new Node[256];
    }
    
    public void add(int key) {
        int index = hash(key);
        Node head = array[index];
        Node node = head;
        // case 1: exist
        while (node != null) {
            if (node.key == key) {
                return;
            }
            node = node.next;
        }
        // case 2: not exist
        Node newNode = new Node(key);
        newNode.next = array[index];
        array[index] = newNode;
        size++;
        if ((double)size / array.length >= loadFactor) {
            rehash();
        }
    }
    
    private int hash(int key) {
        int hashNumber = Integer.hashCode(key);
        return (hashNumber & 0X7FFFFFFF) % array.length;
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
    
    public boolean contains(int key) {
        int index = hash(key);
        Node node = array[index];
        while (node != null) {
            if (node.key == key) {
                return true;
            }
            node = node.next;
        }
        return false;
    }
}
