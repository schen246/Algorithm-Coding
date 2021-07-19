import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class AllNodesDistanceKInBinaryTree {
    // bfs build graph + bfs k distance - time: O(n) space: O(n)
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        Map<TreeNode, List<TreeNode>> map = new HashMap<>();
        buildGraph(root, map);
        return bfs(root, target, map, k);
    }
    
    private List<Integer> bfs(TreeNode root, TreeNode target, Map<TreeNode, List<TreeNode>> map, int k) {
        List<Integer> res = new ArrayList<>();
        Queue<TreeNode> q = new ArrayDeque<>();
        Set<TreeNode> set = new HashSet<>();
        set.add(target);
        q.offer(target);
        int steps = 0;
        while (q.size() > 0) {
            int size = q.size();
            if (steps == k) {
                break;
            }
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                for (TreeNode nei : map.get(cur)) {
                    if (set.add(nei)) {
                        q.offer(nei);
                    }
                }
            }
            steps++;
        }
        while (q.size() > 0) {
            res.add(q.poll().val);
        }
        return res;
    }
    
    private void buildGraph(TreeNode root, Map<TreeNode, List<TreeNode>> map) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        map.put(root, new ArrayList<>());
        while (q.size() > 0) {
            TreeNode cur = q.poll();
            if (cur.left != null) {
                map.get(cur).add(cur.left);
                q.offer(cur.left);
                map.put(cur.left, new ArrayList<>());
                map.get(cur.left).add(cur);
            }
            if (cur.right != null) {
                map.get(cur).add(cur.right);
                q.offer(cur.right);
                map.put(cur.right, new ArrayList<>());
                map.get(cur.right).add(cur);
            }
        }
    }
}
