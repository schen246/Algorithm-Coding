public class BinaryTreeMaximumPathSum {
    public int maxPathSum(TreeNode root) {
        int[] res = new int[1];
        res[0] = Integer.MIN_VALUE;
        helper(root, res);
        return res[0];
    }

    private int helper(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        left = left < 0 ? 0 : left;
        right = right < 0 ? 0 : right;
        res[0] = Math.max(res[0], root.val + left + right);
        return Math.max(left, right) + root.val;
    }
    // time: O(n) space: O(H)
}
