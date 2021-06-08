public class RemoveLinkedListElements {
    // time: O(n) space: O(1)
    public ListNode removeElements(ListNode head, int val) {
        ListNode d = new ListNode(-1);
        d.next = head;
        ListNode pre = d, cur = head;
        while (cur != null) {
            if (cur.val == val) {
                pre.next = cur.next;
            } else {
                pre = cur;
            }
            cur = cur.next;
        }
        return d.next;
    }
}
