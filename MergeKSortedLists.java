public class MergeKSortedLists {
    // Q1: merge k sorted lists
    // M1: k-way merge, time O(knlogk) space O(k)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> pq = new PriorityQueue<>((a, b) -> (a.val - b.val));
        for (ListNode li : lists) {
            if (li != null) {
                pq.offer(li);
            }
        }
        ListNode head = pq.peek();
        ListNode res = head;
        while (!pq.isEmpty()) {
            ListNode cur = pq.poll();
            res.next = cur;
            res = res.next;
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }
        return head;
    }

    // M2: two-way merge recursively, time O(knlogk) space O(logk)
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeK(lists, 0, lists.length - 1);
    }

    private ListNode mergeK(ListNode[] lists, int i, int j) {
        if (i == j) {
            return lists[i];
        }
        int mid = i + (j - i) / 2;
        ListNode left = mergeK(lists, i, mid);
        ListNode right = mergeK(lists, mid + 1, j);
        return mergeTwoLists(left, right);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode d = new ListNode(0);
        ListNode cur = d;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        if (l1 != null) {
            cur.next = l1;
        }
        if (l2 != null) {
            cur.next = l2;
        }
        return d.next;
    }

    // Q2: ways to select k numbers
    // index 0 1 2
    // freq  2 2 1
    // k = 2
    // select k numbers, how many ways you can select?

    // solution: dfs
    //                 ""
    // 0       0       1       2
    // 1   0   1   2
    // 2 0   1
    // time O(min(arr[0], k) * min(arr[1], k) * ...)
    // space O(arr.length)

    public int uniqueWaysWithKNumbers(int[] arr, int k) {
        return dfs(arr, k, 0);
    }

    private int dfs(int[] arr, int k, int index) {
        if (k == 0) return 1; // check k == 0 first
        if (index >= arr.length) return 0;
        int res = 0;
        for (int i = 0; i <= arr[index]; i++) {
            if (k - i < 0) return res; // early return
            res += dfs(arr, k - i, index + 1);
        }
        return res;
    }

    // Q3: find k closest points to origin (0, 0)
    // input: int[][] points, int k
    // output: int[][]
    // solution: maxHeap with k points
    // time: O(nlogk)
    // space: O(k)

    public List<int[]> kClosestPoints(int[][] points, int k) {
        // assume k < points.length
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> (b[0] * b[0] + b[1] * b[1] - a[0] * a[0] - a[1] * a[1]));
        for (int[] point : points) {
            pq.offer(point);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<int[]> res = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            res.add(pq.poll());
        }
        return res;
    }
}




