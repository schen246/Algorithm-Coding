public class CountUnivalueSubtrees {
    // divide and conquer - time: O(n) space: O(height)
    public int countUnivalSubtrees(TreeNode root) {
        int[] res = new int[1];
        helper(root, res);// helper2(root, res, -1)
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

    private boolean helper2(TreeNode root, int[] result, int prev) {
        if (root == null) return true;
        boolean left = helper2(root.left, result, root.val);
        boolean right = helper2(root.right, result, root.val);
        if (!left || !right) return false;
        result[0]++;
        return root.val == prev;
    }
}
