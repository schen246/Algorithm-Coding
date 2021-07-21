package Amazon.Coding;

public class MaximumAverageSubtree {
    // divide and conquer - time: O(n) space: O(height)
    public double maximumAverageSubtree(TreeNode root) {
        double[] res = new double[1];
        res[0] = Double.MIN_VALUE;
        helper(root, res);
        return res[0];
    }
    
    private int[] helper(TreeNode root, double[] res) {
        if (root == null) {
            return null;
        }
        int[] left = helper(root.left, res);
        int[] right = helper(root.right, res);
        int sum = root.val, cnt = 1;
        if (left != null) {
            sum += left[0];
            cnt += left[1];
        }
        if (right != null) {
            sum += right[0];
            cnt += right[1];
        }
        res[0] = Math.max(res[0], (double)sum / cnt);
        return new int[]{sum, cnt};
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}