import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {
    public Node cloneGraph(Node node) {
        // M1: dfs time: O(n) space: O(n)
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        dfs(node, map);// bfs(node, map)
        return map.get(node);
    }

    private void dfs(Node node, Map<Node, Node> map) {
        Node copyNode = new Node(node.val);
        map.put(node, copyNode);
        for (Node nei : node.neighbors) {
            if (!map.containsKey(nei)) {
                dfs(nei, map);
            }
            copyNode.neighbors.add(map.get(nei));
        }
    }

    // M2: bfs time: O(n) space: O(n)
    private void bfs(Node node, Map<Node, Node> map) {
        Queue<Node> q = new ArrayDeque<>();
        q.offer(node);
        map.put(node, new Node(node.val));
        while (q.size() > 0) {
            Node cur = q.poll();
            Node copyNode = map.get(cur);
            for (Node nei : cur.neighbors) {
                if (!map.containsKey(nei)) {
                    q.offer(nei);
                    map.put(nei, new Node(nei.val));
                }
                copyNode.neighbors.add(map.get(nei));
            }
        }
    }

}

class Node {
    public int val;
    List<Node> neighbors = new ArrayList<>();
    public Node(int val) {
        this.val = val;
    }
}