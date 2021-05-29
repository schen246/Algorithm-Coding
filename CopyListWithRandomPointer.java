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
    // time: O(n) space: O(n)
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