import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> {
            if (map.get(a) == map.get(b)) {
                return b.compareTo(a);// higher alphabetical - O(s.length())
            }
            return map.get(a) - map.get(b);// min heap
        });
        for (String s : map.keySet()) {
            pq.offer(s);
            if (pq.size() > k) {
                pq.poll();
            }
        }
        List<String> res = new ArrayList<>();
        while (pq.size() > 0) {
            res.add(pq.poll());
        }
        Collections.reverse(res);
        return res;
    }
    // time: O(|words| * logk * O(s.length())) + O(k) space: O(k)
}
