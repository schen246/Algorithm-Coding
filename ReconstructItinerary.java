import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class ReconstructItinerary {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            map.putIfAbsent(from, new ArrayList<>());
            map.get(from).add(to);
        }
        for (List<String> i : map.values()) {//O(d * log d)
            Collections.sort(i);
        }
        int n = tickets.size() + 1;
        res.add("JFK");
        dfs("JFK", n, map, res);
        return res;
    }

    private boolean dfs(String s, int n, Map<String, List<String>> map, List<String> res) {
        if (res.size() == n) {
            return true;
        }
        if (!map.containsKey(s)) {
            return false;
        }
        List<String> destinations = map.get(s);
        for (int i = 0; i < destinations.size(); i++) {
            String d = destinations.get(i);
            destinations.remove(i);
            res.add(d);
            if (dfs(d, n, map, res)) {
                return true;
            }
            res.remove(res.size() - 1);
            destinations.add(i, d);
        }
        return false;
    }
    // time: O(sum(d * log d) + V^E) space: O(V + E)
}

class ReconstructItinerary2 {
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> res = new ArrayList<>();
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0), to = ticket.get(1);
            map.putIfAbsent(from, new PriorityQueue<>());
            map.get(from).offer(to);
        }
        dfs("JFK", map, res);
        return res;
    }

    private void dfs(String s, Map<String, PriorityQueue<String>> map, List<String> res) {
        PriorityQueue<String> pq = map.get(s);
        while (pq != null && pq.size() > 0) {
            String nei = pq.poll();
            dfs(nei, map, res);
        }
        res.add(0, s);
    }
    // time: O(E * log E) space: O(V + E)
}