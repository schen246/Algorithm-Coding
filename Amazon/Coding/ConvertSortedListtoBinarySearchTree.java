package Amazon.Coding;

import java.util.ArrayList;
import java.util.List;

public class ConvertSortedListtoBinarySearchTree {
    public TreeNode sortedListToBST(ListNode head) {
        List<Integer> list = new ArrayList<>();
        while (head != null) {
            list.add(head.val);
            head = head.next;
        }
        return convert(list, 0, list.size() - 1);
    }
    
    private TreeNode convert(List<Integer> list, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode node = new TreeNode(list.get(mid));
        node.left = convert(list, left, mid - 1);
        node.right = convert(list, mid + 1, right);
        return node;
    }
}
