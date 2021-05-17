public class LargestBSTSubtree {
    public int largestBSTSubtree(TreeNode root) {
        int[] res = new int[1];
        helper(root, res);
        return res[0];
    }

    private int[] helper(TreeNode root, int[] res) {
        if (root == null) {
            return new int[]{0, Integer.MAX_VALUE, Integer.MIN_VALUE};//nodes, min, max
        }
        int[] left = helper(root.left, res);
        int[] right = helper(root.right, res);
        int[] ans = new int[3];
        if (left[0] != -1 && right[0] != -1 && left[2] < root.val && root.val < right[1]) {
            ans[0] = left[0] + right[0] + 1;//nodes
            ans[1] = Math.min(left[1], root.val);
            ans[2] = Math.max(right[2], root.val);
            res[0] = Math.max(res[0], ans[0]);
        } else {
            ans[0] = -1;
        }
        return ans;
    }
    // time: O(n) space: O(height)
}
