public class MiddleOfTheLinkedList {
    public ListNode middleNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode s = head, f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        if (f.next != null) {
            return s;
        }
        return s.next;
    }
    // time: O(n) space: O(1)
}
