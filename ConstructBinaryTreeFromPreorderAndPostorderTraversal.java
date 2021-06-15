import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndPostorderTraversal {
    // divide and conquer + map - time: O(n) space: O(n) + O(height)
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < post.length; i++) {
            map.put(post[i], i);
        }
        return helper(pre, 0, pre.length - 1, post, 0, post.length - 1, map);
    }
    
    private TreeNode helper(int[] pre, int preLeft, int preRight, int[] post, int postLeft, int postRight, Map<Integer, Integer> map) {
        if (preLeft > preRight) {
            return null;
        }
        int val = pre[preLeft];
        TreeNode root = new TreeNode(val);
        if (preLeft == preRight) {
            return root;
        }
        int index = map.get(pre[preLeft + 1]);
        int len = index - postLeft + 1;
        root.left = helper(pre, preLeft + 1, preLeft + len, post, postLeft, postLeft + len - 1, map);
        root.right = helper(pre, preLeft + len + 1, preRight, post, postLeft + len, postRight - 1, map);
        return root;
    }
}
