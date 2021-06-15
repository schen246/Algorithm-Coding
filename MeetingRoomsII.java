import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        List<int[]> p = new ArrayList<>();
        for (int[] interval : intervals) {
            p.add(new int[]{interval[0], -1});// start
            p.add(new int[]{interval[1], 1});// end
        }
        Collections.sort(p, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];// end first
            }
            return a[0] - b[0];// small first
        });
        int res = 0, cnt = 0;
        for (int[] cur : p) {
            if (cur[1] == -1) {
                cnt++;
            } else {
                cnt--;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }
    // sweep line - sort - time: O(nlogn) space: O(n)

    public int minMeetingRooms2(int[][] intervals) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });
        for (int[] interval : intervals) {
            pq.offer(new int[]{interval[0], -1});
            pq.offer(new int[]{interval[1], 1});
        }
        int res = 0, cnt = 0;
        while (pq.size() > 0) {
            int[] cur = pq.poll();
            if (cur[1] == -1) {
                cnt++;
            } else {
                cnt--;
            }
            res = Math.max(res, cnt);
        }
        return res;
    }
    // sweep line - heap - time: O(nlogn) space: O(n)
}
