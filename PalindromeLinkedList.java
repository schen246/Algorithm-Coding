public class PalindromeLinkedList {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        ListNode mid = findMid(head);// time: O(n) space: O(1)
        ListNode node = mid.next;
        mid.next = null;
        ListNode head2 = reverse(node);// time: O(n/2) space: O(1)
        return compareTwoList(head, head2);// time: O(n) space: O(1)
    }
    // time: O(n) space: O(1)

    private ListNode findMid(ListNode head) {
        ListNode s = head, f = head;
        while (f.next != null && f.next.next != null) {
            s = s.next;
            f = f.next.next;
        }
        return s;
    }

    private ListNode reverse(ListNode cur) {
        ListNode pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private boolean compareTwoList(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null) {
            if (l1.val != l2.val) {
                return false;
            }
            l1 = l1.next;
            l2 = l2.next;
        }
        return true;
    }
}
