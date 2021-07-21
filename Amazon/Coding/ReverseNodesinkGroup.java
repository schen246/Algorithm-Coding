package Amazon.Coding;

public class ReverseNodesinkGroup {
    // step1: find k nodes
    // step2: reverse k nodes
    // step3: pre -> newNode, pre = start, start = end = next, cnt = 0
    // step4: pre.next = start
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode d = new ListNode(0);
        ListNode pre = d, start = head, end = head;
        int cnt = 0;
        while (end != null) {
            cnt++;
            if (cnt == k) {
                ListNode next = end.next;
                pre.next = reverse(start, end);
                pre = start;
                start = next;
                end = next;
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
            ListNode next = start.next;
            start.next = pre;
            pre = start;
            start = next;
        }
        end.next = pre;
        return end;
    }
}
