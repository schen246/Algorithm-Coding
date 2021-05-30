public class OddEvenLinkedList {
    // time: O(n) space: O(1)
    public ListNode oddEvenList(ListNode head) {
        ListNode d1 = new ListNode(1);
        ListNode d2 = new ListNode(2);
        ListNode l1 = d1, l2 = d2;
        int cnt = 0;
        while (head != null) {
            if (cnt % 2 == 0) {
                l1.next = head;
                l1 = l1.next;
            } else {
                l2.next = head;
                l2 = l2.next;
            }
            head = head.next;
            cnt++;
        }
        l2.next = null;
        l1.next = d2.next;
        return d1.next;
    }
}
