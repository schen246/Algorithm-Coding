public class ReverseLinkedListII {
    // iteration - time: O(n) space: O(1)
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode d = new ListNode(-1);
        d.next = head;
        ListNode pre = d, cur = head;
        int i = 1;
        while (i < m) {
            pre = cur;
            cur = cur.next;
            i++;
        }
        pre.next = reverse(cur, n - m + 1);// reverse k elements with new head
        return d.next;
    }

    private ListNode reverse(ListNode head, int len) {// time: O(len) space: O(1)
        ListNode pre = null, cur = head;
        int i = 1;
        while (i <= len) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
            i++;
        }
        head.next = cur;
        return pre;
    }
}
