import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class BinaryTreeVerticalOrderTraversal {
    // M1: bfs + map + int min/max - time: O(n) space: O(n)
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<Integer>> map = new HashMap<>();// x, list of val
        Queue<Node> q = new ArrayDeque<>();// Node(root, x)
        q.offer(new Node(root, 0));
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        while (q.size() > 0) {
            Node cur = q.poll();
            TreeNode node = cur.node;
            int x = cur.x;
            map.putIfAbsent(x, new ArrayList<>());
            map.get(x).add(node.val);
            minVal = Math.min(minVal, node.val);
            maxVal = Math.max(maxVal, node.val);
            if (node.left != null) {
                q.offer(new Node(node.left, x - 1));
            }
            if (node.right != null) {
                q.offer(new Node(node.right, x + 1));
            }
        }
        for (int i = minVal; i <= maxVal; i++) {
            res.add(map.get(i));
        }
        return res;
    }
}

class Node {
    TreeNode node;
    int x;
    public Node(TreeNode node, int x) {
        this.node = node;
        this.x = x;
    }
}