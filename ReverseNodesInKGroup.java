public class ReverseNodesInKGroup {
    // time: O(n) space: O(1)
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode d = new ListNode(0);
        ListNode pre = d;
        ListNode start = head, end = head;
        int cnt = 0;
        while (end != null) {
            cnt++;
            if (cnt == k) {
                ListNode node = end.next;
                ListNode newHead = reverse(start, end);
                pre.next = newHead;
                pre = start;
                start = node;
                end = node;
                cnt = 0;
            } else {
                end = end.next;
            }
        }
        pre.next = start;
        return d.next;
    }

    private ListNode reverse(ListNode start, ListNode end) {
        ListNode pre = null;
        while (start != end) {
            ListNode node = start.next;
            start.next = pre;
            pre = start;
            start = node;
        }
        end.next = pre;
        return end;
    }
}
