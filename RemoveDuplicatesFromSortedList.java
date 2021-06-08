public class RemoveDuplicatesFromSortedList {
    // time: O(n) space: O(1)
    public ListNode deleteDuplicates(ListNode head) { 
        ListNode pre = null, cur = head;
        while (cur != null) {
            if (pre != null && cur.val == pre.val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return head;
    }
}
