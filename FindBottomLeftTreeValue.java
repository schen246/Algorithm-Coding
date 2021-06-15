import java.util.ArrayDeque;
import java.util.Queue;

public class FindBottomLeftTreeValue {
    // M1: bfs - time: O(n) space: O(max row)
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        int res = -1;
        while (q.size() > 0) {
            int size = q.size();
            res = q.peek().val;
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
        }
        return res;
    }

    // M2: dfs - time: O(n) space: O(height)
    public int findBottomLeftValue2(TreeNode root) {
        int[] res = new int[2];// val, maxDepth
        dfs(root, 1, res);
        return res[0];
    }
    
    private void dfs(TreeNode node, int depth, int[] res) {
        if (node == null) {
            return;
        }
        if (depth > res[1]) {
            res[0] = node.val;
            res[1] = depth;
        }
        depth++;
        dfs(node.left, depth, res);
        dfs(node.right, depth, res);
    }
}
