public class LinkedListCycleII {
    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode s = head, f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
            if (s == f) {
                break;
            }
        }
        if (f.next != null && f.next.next != null) {
            while (head != s) {
                head = head.next;
                s = s.next;
            }
            return head;
        }
        return null;
    }
    // time: O(n) space: O(1)
}
