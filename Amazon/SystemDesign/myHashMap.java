package Amazon.SystemDesign;

/*
HashMap implementation with generic type
Methods:
- get(K key)
- put(K key, V val)
- remove(K key)
*/
public class myHashMap<K, V> {
    public static class Entry<K, V> {
        K key;
        V val;
        Entry<K, V> next;
        public Entry(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_FACTOR = 0.75;

    private Entry<K, V>[] array;
    private int size;
    private double loadFactor;

    public myHashMap() {
        this(DEFAULT_CAPACITY, DEFAULT_FACTOR);
    }

    public myHashMap(int cap, double loadFactor) {
        if (size <= 0) {
            throw new IllegalArgumentException("size can not be <= 0");
        }
        array = new Entry[cap];
        this.size = 0;
        this.loadFactor = loadFactor;
    }

    public V get(K key) {
        int index = hash(key);
        Entry<K, V> head = array[index];
        while (head != null) {
            if (equalsKey(head.key, key)) {
                return head.val;
            }
            head = head.next;
        }
        return null;
    }

    public V put(K key, V val) {
        int index = hash(key);
        Entry<K, V> head = array[index];
        Entry<K, V> node = head;
        // case 1: exist
        while (node != null) {
            if (equalsKey(node.key, key)) {
                V result = node.val;
                node.val = val;
                return result;
            }
            node = node.next;
        }
        // case 2: not exist
        Entry<K, V> newNode = new Entry(key, val);
        newNode.next = head;
        array[index] = newNode;
        size++;
        if (needRehashing()) {
            rehash();
        }
        return null;
    }

    public V remove(K key) {
        int index = hash(key);
        Entry<K, V> cur = array[index];
        Entry<K, V> pre = null;
        while (cur != null) {
            if (equalsKey(cur.key, key)) {
                if (pre == null) {
                    array[index] = cur.next;
                } else {
                    pre.next = cur.next;
                }
                size--;
                return cur.val;
            }
            pre = cur;
            cur = cur.next;
        }
        return null;
    }

    private void rehash() {
        Entry<K, V>[] oldArray = array;
        array = (Entry<K, V>[]) new Entry[array.length * 2];
        for (Entry<K, V> cur : oldArray) {
            while (cur != null) {
                Entry<K, V> next = cur.next;
                int index = hash(cur.key);
                // move cur to the front of array[index]
                cur.next = array[index];
                array[index] = cur;
                cur = next;
            }
        }
    }

    private boolean needRehashing() {
        double ratio = (double)size / array.length;
        return ratio >= loadFactor;
    }

    private boolean equalsKey(K key1, K key2) {
        if (key1 == null && key2 == null) {
            return true;
        }
        if (key1 == null || key2 == null) {
            return false;
        }
        return key1.equals(key2);
    }

    private int hash(K key) {
        if (key == null) {
            return 0;
        }
        int hashNumber = key.hashCode();
        return (hashNumber & 0X7FFFFFFF) % array.length; // make sure hashNumber non-negative
    }
}
