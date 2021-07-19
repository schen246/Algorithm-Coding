package Amazon;

public class FindDistanceinaBinaryTree {
    // recursion - time: O(n) space: O(height)
    public int findDistance(TreeNode root, int p, int q) {
        if (p == q) {
            return 0;
        }
        int[] res = dfs(root, p, q);
        if (res[0] == 2) {
            return res[1];
        }
        return -1;
    }

    private int[] dfs(TreeNode root, int p, int q) {
        if (root == null) {
            return new int[]{0, 0};
        }
        int[] left = dfs(root.left, p, q);
        int[] right = dfs(root.right, p, q);
        if (left[0] == 2) return left;
        if (right[0] == 2) return right;
        int cnt = (root.val == p || root.val == q) ? 1 : 0;
        if (left[0] == 1) return new int[]{cnt + 1, left[1] + 1};
        if (right[0] == 1) return new int[]{cnt + 1, right[1] + 1};
        return new int[]{cnt, 0};
    }

    // recursion with global res - time: O(n) space: O(height)
    public int findDistance2(TreeNode root, int p, int q) {
        if (p == q) {
            return 0;
        }
        int[] res = new int[1];
        dfs(root, p, q, res);
        return res[0];
    }
    
    private int dfs(TreeNode root, int p, int q, int[] res) {
        if (root == null) {
            return -1;
        }
        int left = dfs(root.left, p, q, res);
        int right = dfs(root.right, p, q, res);
        if (root.val == p || root.val == q) {
            if (left == - 1 && right == -1) {
                return 0;
            }
            res[0] = (left == -1 ? right : left) + 1;
            return -1;
        }
        if (left != -1 && right != -1) {
            res[0] = left + right + 2;
            return -1;
        }
        if (left != -1) {
            return left + 1;
        }
        if (right != -1) {
            return right + 1;
        }
        return -1;
    }
}

