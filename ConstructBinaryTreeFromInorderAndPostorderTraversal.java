import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromInorderAndPostorderTraversal {
    // divide and conquer + map - time: O(n) space: O(n)
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return helper(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    private TreeNode helper(int[] inorder, int inLeft, int inRight, int[] postorder, int poLeft, int poRight, Map<Integer, Integer> map) {
        if (inLeft > inRight) {
            return null;
        }
        int val = postorder[poRight];
        int index = map.get(val);
        TreeNode root = new TreeNode(val);
        int len = index - inLeft;
        root.left = helper(inorder, inLeft, inLeft + len - 1, postorder, poLeft, poLeft + len - 1, map);
        root.right = helper(inorder, index + 1, inRight, postorder, poLeft + len, poRight - 1, map);
        return root;
    }
}
