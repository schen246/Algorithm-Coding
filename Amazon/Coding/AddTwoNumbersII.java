package Amazon.Coding;

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}

public class AddTwoNumbersII {
    // M1: not modify l1, l2
    // time: O(m + n) space: O(1)
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int n1 = 0, n2 = 0 ;
        ListNode cur1 = l1, cur2 = l2;
        while (cur1 != null) {
            n1++;
            cur1 = cur1.next;
        }
        while (cur2 != null) {
            n2++;
            cur2 = cur2.next;
        }
        cur1 = l1;
        cur2 = l2;
        ListNode node = null;
        while (n1 > 0 && n2 > 0) {
            int val = 0;
            if (n1 == n2) {
                val += cur1.val;
                cur1 = cur1.next;
                n1--;
                val += cur2.val;
                cur2 = cur2.next;
                n2--;
            } else if (n1 > n2) {
                val += cur1.val;
                cur1 = cur1.next;
                n1--;
            } else {
                val += cur2.val;
                cur2 = cur2.next;
                n2--;
            }
            ListNode newNode = new ListNode(val);
            newNode.next = node;
            node = newNode;
        }
        ListNode cur = null;
        int rem = 0;
        while (node != null || rem != 0) {
            if (node != null) {
                rem += node.val;
                node = node.next;        
            }
            ListNode temp = new ListNode(rem % 10);
            temp.next = cur;
            cur = temp;
            rem /= 10;
        }
        return cur;
    }

    // M2: reverse input + add number in fromt
    // time: O(m + n) space: O(1)
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        return merge(reverse(l1), reverse(l2));
    }
    
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode cur = null;
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
            ListNode node = new ListNode(rem % 10);
            node.next = cur;
            cur = node;
            rem /= 10;
        }
        return cur;
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
}
