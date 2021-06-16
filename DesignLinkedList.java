public class DesignLinkedList {
    class Node {
        int val;
        Node next;
        public Node(int val) {
            this.val = val;
        }
    }
    
    Node head, tail;
    int size;
    public DesignLinkedList() {
        head = new Node(0);
        tail = head;
        size = 0;
    }
    
    public int get(int index) {
        if (index < 0 || index >= size) {
            return -1;
        }
        Node cur = head;
        for (int i = 0; i <= index; i++) {
            cur = cur.next;
        }
        return cur.val;
    }
    
    public void addAtHead(int val) {
        Node next = head.next;
        head.next = new Node(val);
        head.next.next = next;
        if (size == 0) {
            tail = head.next;
        }
        size++;
    }
    
    public void addAtTail(int val) {
        tail.next = new Node(val);
        tail = tail.next;
        size++;
    }
    
    public void addAtIndex(int index, int val) {
        if (index > size || index < 0) {
            return;
        }
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        Node next = cur.next;
        cur.next = new Node(val);
        cur.next.next = next;
        if (index == size) {
            tail = tail.next;
        }
        size++;
    }
    
    public void deleteAtIndex(int index) {
        if (index >= size || index < 0) {
            return;
        }
        Node cur = head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        if (index == size - 1) {
            tail = cur;
        }
        size--;
    }
}

