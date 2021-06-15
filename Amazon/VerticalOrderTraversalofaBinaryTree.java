package Amazon;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class VerticalOrderTraversalofaBinaryTree {
    // bfs + map + sort - time: O(n) + O(nlogn) space: O(n)
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Map<Integer, List<int[]>> map = new HashMap<>();// replace List<int[]> -> PriorityQueue<int[]>也可以
        Queue<Node> q = new ArrayDeque<>();
        q.offer(new Node(root, 0, 0));// node, x, y
        int minVal = Integer.MAX_VALUE, maxVal = Integer.MIN_VALUE;
        while (q.size() > 0) {
            Node cur = q.poll();
            TreeNode node = cur.node;
            int x = cur.x, y = cur.y;
            map.putIfAbsent(y, new ArrayList<>());
            map.get(y).add(new int[]{x, node.val});
            minVal = Math.min(minVal, y);
            maxVal = Math.max(maxVal, y);
            if (node.left != null) {
                q.offer(new Node(node.left, x + 1, y - 1));
            }
            if (node.right != null) {
                q.offer(new Node(node.right, x + 1, y + 1));
            }
        }
        for (int i = minVal; i <= maxVal; i++) {
            List<int[]> cur = map.get(i);
            Collections.sort(cur, (a, b) -> {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];// up first
            });
            List<Integer> temp = new ArrayList<>();
            for (int[] p : cur) {
                temp.add(p[1]);
            }
            res.add(temp);
        }
        return res;
    }
}

class Node {
    TreeNode node;
    int x;
    int y;
    public Node(TreeNode node, int x, int y) {
        this.node = node;
        this.x = x;
        this.y = y;
    }
}