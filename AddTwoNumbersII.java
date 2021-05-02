public class AddTwoNumbersII {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = reverse(l1);
        ListNode n2 = reverse(l2);
        return reverse(merge(n1, n2));
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null, cur = head;
        while (cur != null) {
            ListNode node = cur.next;
            cur.next = prev;
            prev = cur;
            cur = node;
        }
        return prev;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode d = new ListNode(0);
        ListNode cur = d;
        int rem = 0;
        while (l1 != null || l2 != null || rem != 0) {
            if (l1 != null) {
                rem += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                rem += l2.val;
                l2 = l2.next;
            }
            cur.next = new ListNode(rem % 10);
            cur = cur.next;
            rem /= 10;
        }
        return d.next;
    }
}
