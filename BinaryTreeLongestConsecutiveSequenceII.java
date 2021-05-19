public class BinaryTreeLongestConsecutiveSequenceII {
    public int longestConsecutive(TreeNode root) {
        int[] res = new int[1];
        helper(root, res);
        return res[0];
    }

    private int[] helper(TreeNode root, int[] res) {
        if (root == null) {
            return null;
        }
        int[] left = helper(root.left, res);
        int[] right = helper(root.right, res);
        int ascL = 0, ascR = 0, desL = 0, desR = 0;
        if (left != null) {
            if (root.val + 1 == root.left.val) {
                ascL = left[0];
            }
            if (root.val - 1 == root.left.val) {
                desL = left[1];
            }
        }
        if (right != null) {
            if (root.val + 1 == root.right.val) {
                ascR = right[0];
            }
            if (root.val - 1 == root.right.val) {
                desR = right[1];
            }
        }
        res[0] = Math.max(res[0], Math.max(ascL + desR + 1, desL + ascR + 1));
        return new int[]{Math.max(ascL, ascR) + 1, Math.max(desL, desR) + 1};
    }
    // recursion - time: O(n) space: O(n)
}
