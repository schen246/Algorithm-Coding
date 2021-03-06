package Amazon.Coding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ReorganizeString {
    class Item {
        char letter;
        int cnt;
        public Item(char letter, int cnt) {
            this.letter = letter;
            this.cnt = cnt;
        }
    }
    // map + pq - time: O(nlogn) space: O(n)
    public String reorganizeString(String s) {
        Map<Character, Integer> map = new HashMap<>();// <letter, cnt>
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            if (a.cnt == b.cnt) {
                return a.letter - b.letter;// small letter first
            }
            return b.cnt - a.cnt;// large cnt first
        });
        for (char c : map.keySet()) {
            pq.offer(new Item(c, map.get(c)));
        }
        StringBuilder sb = new StringBuilder();
        int k = 2;
        while (pq.size() > 0) {
            List<Character> list = new ArrayList<>();
            for (int i = 0; i < k; i++) {
                Item cur = pq.poll();
                sb.append(cur.letter);
                list.add(cur.letter);
                map.put(cur.letter, map.get(cur.letter) - 1);
                if (pq.size() == 0) {
                    if (i != k - 1 && sb.length() != s.length()) {
                        return "";
                    }
                    break;
                }
            }
            for (char c : list) {
                if (map.get(c) > 0) {
                    pq.offer(new Item(c, map.get(c)));
                }
            }
        }
        return sb.toString();
    }

    // map + pq - time: O(nlogn) space: O(n)
    public String reorganizeString2(String s) {
        Map<Character, Integer> map = new HashMap<>();// <letter, cnt>
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            if (a.cnt == b.cnt) {
                return a.letter - b.letter;// small letter first
            }
            return b.cnt - a.cnt;// large cnt first
        });
        for (char c : map.keySet()) {
            pq.offer(new Item(c, map.get(c)));
        }
        StringBuilder sb = new StringBuilder();
        Item pre = null;
        while (pq.size() > 0) {
            Item cur = pq.poll();
            if (pre != null && pre.cnt > 0) {
                pq.offer(pre);
            }
            sb.append(cur.letter);
            cur.cnt--;
            pre = cur;
        }
        return sb.length() == s.length() ? sb.toString() : "";
    }
}
