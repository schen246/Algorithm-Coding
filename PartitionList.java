public class PartitionList {
    // time: O(n) space: O(1)
    public ListNode partition(ListNode head, int x) {
        ListNode l1 = new ListNode(-1);
        ListNode l2 = new ListNode(0);
        ListNode d1 = l1, d2 = l2;
        while (head != null) {
            if (head.val < x) {
                d1.next = head;
                d1 = d1.next;
            } else {
                d2.next = head;
                d2 = d2.next;
            }
            head = head.next;
        }
        d1.next = l2.next;
        d2.next = null;
        return l1.next;
    }
}
