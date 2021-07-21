package Amazon.Coding;

public class AddTwoNumbers {
    // time: O(m + n) space: O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode d = new ListNode(0);
        ListNode pre = d;
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
            ListNode cur = new ListNode(rem % 10);
            pre.next = cur;
            pre = cur;
            rem /= 10;
        }
        return d.next;
    }
}
