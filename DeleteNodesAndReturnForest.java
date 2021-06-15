import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DeleteNodesAndReturnForest {
    // dfs - time: O(n) space: O(height)
    public List<TreeNode> delNodes(TreeNode root, int[] to_delete) {
        Set<Integer> set = new HashSet<>();
        for (int val : to_delete) {
            set.add(val);
        }
        List<TreeNode> res = new ArrayList<>();
        dfs(root, null, set, res);
        return res;
    }

    private void dfs(TreeNode cur, TreeNode pre, Set<Integer> set, List<TreeNode> res) {
        // base case
        if (cur == null) {
            return;
        }
        // add res
        if (pre == null || set.contains(pre.val)) {
            if (!set.contains(cur.val)) {
                res.add(cur);
            }
        }
        // divide and conquer
        dfs(cur.left, cur, set, res);
        dfs(cur.right, cur, set, res);
        // post process
        if (set.contains(cur.val)) {
            cur.left = null;
            cur.right = null;
            if (pre != null && cur == pre.left) {
                pre.left = null;
            }
            if (pre != null && cur == pre.right) {
                pre.right = null;
            }
        }
    }
}
