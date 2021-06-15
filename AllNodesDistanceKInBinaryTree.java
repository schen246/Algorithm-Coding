import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AllNodesDistanceKInBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        // assume: tree not empty, unique node, target in tree
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        buildGraph(map, root);
        return bfs(map, target, K);
    }

    private List<Integer> bfs(Map<TreeNode, List<TreeNode>> map, TreeNode target, int k) {
        List<Integer> res = new ArrayList<>();
        if (k == 0) {
            res.add(target.val);
            return res;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        Set<TreeNode> set = new HashSet<>();
        q.offer(target);
        set.add(target);
        while (q.size() > 0) {
            int n = q.size();
            k--;
            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                for (TreeNode nei : map.get(node)) {
                    if (set.add(nei)) {
                        q.offer(nei);
                        if (k == 0) {
                            res.add(nei.val);
                        }
                    }
                }
            }
            if (k == 0) {
                return res;
            }
        }
        return res;
    }

    private void buildGraph(Map<TreeNode, List<TreeNode>> map, TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        map.put(root, new ArrayList<>());
        while (q.size() > 0) {
            TreeNode node = q.poll();
            List<TreeNode> cur = map.get(node);
            if (node.left != null) {
                q.offer(node.left);
                cur.add(node.left);
                map.put(node.left, new ArrayList<>());
                map.get(node.left).add(node);
            }
            if (node.right != null) {
                q.offer(node.right);
                cur.add(node.right);
                map.put(node.right, new ArrayList<>());
                map.get(node.right).add(node);
            }
        }
    }
    // two pass bfs
    // time: O(n) space: O(n)
}
