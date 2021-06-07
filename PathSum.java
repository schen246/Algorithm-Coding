public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return root.val == sum;
        }
        int remain = sum - root.val;
        return hasPathSum(root.left, remain) || hasPathSum(root.right, remain);
    }
    // time: O(n) space: O(height)
}
