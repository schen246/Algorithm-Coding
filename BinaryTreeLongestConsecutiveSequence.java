public class BinaryTreeLongestConsecutiveSequence {
    public int longestConsecutive(TreeNode root) {
        int[] res = new int[1];
        helper(root, res);
        return res[0];
    }

    private int helper(TreeNode root, int[] res) {
        if (root == null) {
            return 0;
        }
        int left = helper(root.left, res);
        int right = helper(root.right, res);
        if (left != 0) {
            if (root.val + 1 != root.left.val) {
                left = 0;
            }
        }
        if (right != 0) {
            if (root.val + 1 != root.right.val) {
                right = 0;
            }
        }
        int ans = Math.max(left, right) + 1;
        res[0] = Math.max(res[0], ans);
        return ans;
    }
    // time: O(n) space: O(H)
}
