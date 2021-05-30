public class IntersectionOfTwoLinkedLists {
    // time: O(m + n) space: O(1)
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        int lenA = findLen(headA);
        int lenB = findLen(headB);
        int len = Math.abs(lenA - lenB);
        if (lenA > lenB) {
            while (len > 0) {
                headA = headA.next;
                len--;
            }
        } else {
            while (len > 0) {
                headB = headB.next;
                len--;
            }
        }
        while (headA != null && headB != null) {
            if (headA == headB) {
                return headA;
            }
            headA = headA.next;
            headB = headB.next;
        }
        return null;
    }

    private int findLen(ListNode head) {
        int res = 0;
        while (head != null) {
            res++;
            head = head.next;
        }
        return res;
    }
}
