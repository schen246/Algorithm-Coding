public class SwapNodesinPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode d = new ListNode(0);
        d.next = head;
        ListNode pre = d;
        while (head != null && head.next != null) {
            ListNode next = head.next.next;
            // reverse
            pre.next = head.next;// new head
            head.next.next = head;// reverse
            head.next = next;
            pre = head;
            head = next;
        }
        return d.next;
    }
}
