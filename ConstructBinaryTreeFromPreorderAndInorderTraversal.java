import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(preorder, 0, preorder.length - 1, map, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] pre, int pL, int pR, Map<Integer, Integer> map, int iL, int iR) {
        if (pL > pR) {
            return null;
        }
        TreeNode root = new TreeNode(pre[pL]);
        int len = map.get(pre[pL]) - iL;
        root.left = helper(pre, pL + 1, pL + len, map, iL, iL + len - 1);
        root.right = helper(pre, pL + len + 1, pR, map, iL + len + 1, iR);
        return root;
    }
    // time: O(n) space: O(height)
}
