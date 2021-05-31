import java.util.Map;

public class CopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        return dfs(head, map);
    }

    private Node dfs(Node node, Map<Node, Node> map) {
        if (node == null) {
            return null;
        }
        if (map.containsKey(node)) {
            return map.get(node);
        }
        Node newNode = new Node(node.val);
        map.put(node, newNode);
        newNode.next = dfs(node.next, map);
        newNode.random = dfs(node.random, map);
        return newNode;
    }
    // time: O(n) space: O(n) map + O(n) call stack

    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Node newHead = new Node(head.val);
        map.put(head, newHead);
        Node cur = newHead;
        while (head != null) {
            if (head.next != null) {
                if (!map.containsKey(head.next)) {
                    map.put(head.next, new Node(head.next.val));
                }
                cur.next = map.get(head.next);
            }
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new Node(head.random.val));
                }
                cur.random = map.get(head.random);
            }
            head = head.next;
            cur = cur.next;
        }
        return newHead;
        // time: O(n) space: O(n) map
    }
}

class Node {
    int val;
    Node next;
    Node random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}