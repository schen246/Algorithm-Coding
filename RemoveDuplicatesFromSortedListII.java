public class RemoveDuplicatesFromSortedListII {
    // time: O(n) space: O(1)
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode d = new ListNode(-1);
        d.next = head;
        ListNode pre = d;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                while (head.next != null && head.val == head.next.val) {
                    head = head.next;
                }
                pre.next = head.next;
            } else {
                pre = head;
            }
            head = head.next;
        }
        return d.next;
    }
}
