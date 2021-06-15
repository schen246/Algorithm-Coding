public class CountUnivalueSubtrees {
    // divide and conquer - time: O(n) space: O(height)
    public int countUnivalSubtrees(TreeNode root) {
        int[] res = new int[1];
        helper(root, res);
        return res[0];
    }

    private boolean helper(TreeNode root, int[] res) {
        if (root == null) {
            return true;
        }
        boolean left = helper(root.left, res);
        boolean right = helper(root.right, res);
        if (left && right) {
            if (root.left != null && root.right != null) {
                if (root.val == root.left.val && root.val == root.right.val) {
                    res[0]++;
                    return true;
                }
            } else if (root.left != null) {
                if (root.val == root.left.val) {
                    res[0]++;
                    return true;
                }
            } else if (root.right != null) {
                if (root.val == root.right.val) {
                    res[0]++;
                    return true;
                }
            } else {
                res[0]++;
                return true;
            }
        }
        return false;
    }
}
