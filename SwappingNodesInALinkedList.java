public class SwappingNodesInALinkedList {
    public ListNode swapNodes(ListNode head, int k) {
        int cnt = 0;
        ListNode cur = head, start = head;
        while (cur != null) {
            cnt++;
            if (cnt == k) {
                start = cur;
            }
            cur = cur.next;
        }
        cur = head;
        while (cnt > k) {
            cnt--;
            cur = cur.next;
        }
        int tmp = start.val;
        start.val = cur.val;
        cur.val = tmp;
        return head;
    }
}
