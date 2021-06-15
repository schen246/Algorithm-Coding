import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Deque<TreeNode> dq = new ArrayDeque<>();
        dq.offerFirst(root);
        int level = 0;
        while (dq.size() > 0) {
            int n = dq.size();
            List<Integer> cur = new ArrayList<>();
            if (level % 2 == 0) {
                for (int i = 0; i < n; i++) {
                    TreeNode node = dq.pollLast();
                    cur.add(node.val);
                    if (node.left != null) dq.offerFirst(node.left);
                    if (node.right != null) dq.offerFirst(node.right);
                }
            } else {
                for (int i = 0; i < n; i++) {
                    TreeNode node = dq.pollFirst();
                    cur.add(node.val);
                    if (node.right != null) dq.offerLast(node.right);
                    if (node.left != null) dq.offerLast(node.left);
                }
            }
            res.add(cur);
            level++;
        }
        return res;
    }

    public List<List<Integer>> zigzagLevelOrder2(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int level = 0;
        while (q.size() > 0) {
            int n = q.size();
            List<Integer> cur = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                TreeNode node = q.poll();
                cur.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            if (level % 2 == 0) {
                res.add(cur);
            } else {
                Collections.reverse(cur);// reverse list
                res.add(cur);
            }
            level++;
        }
        return res;
    }
    // time: O(n) space: O(n)
}
