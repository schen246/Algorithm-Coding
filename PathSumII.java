import java.util.ArrayList;
import java.util.List;

public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        dfs(root, sum, cur, res);
        return res;
    }

    private void dfs(TreeNode root, int sum, List<Integer> cur, List<List<Integer>> res) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            if (sum == root.val) {
                cur.add(root.val);
                res.add(new ArrayList<>(cur));
                cur.remove(cur.size() - 1);
            }
            return;
        }
        cur.add(root.val);
        dfs(root.left, sum - root.val, cur, res);
        dfs(root.right, sum - root.val, cur, res);
        cur.remove(cur.size() - 1);
    }
    // time: O(n) space: O(height)
}
