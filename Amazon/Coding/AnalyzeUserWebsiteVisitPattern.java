package Amazon.Coding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AnalyzeUserWebsiteVisitPattern {
    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {
        Map<String, List<Pair>> userMap = new HashMap<>();// username, list of pair
        for (int i = 0; i < username.length; i++) {// O(n)
            userMap.putIfAbsent(username[i], new ArrayList<>());
            userMap.get(username[i]).add(new Pair(website[i], timestamp[i]));
        }
        for (String s : userMap.keySet()) {
            Collections.sort(userMap.get(s), (a, b) -> a.timestamp - b.timestamp);// O(user.size() log user.size())
        }
        Map<String, Set<String>> sequenceMap = new HashMap<>();// 3-sequence, set of usernames
        for (String user : userMap.keySet()) {// O(user.size())
            List<Pair> list = userMap.get(user);
            for (int i = 0; i < list.size(); i++) {// O(m)
                for (int j = i + 1; j < list.size(); j++) {// O(m)
                    for (int k = j + 1; k < list.size(); k++) {// O(m)
                        String pattern = list.get(i).website + "," + list.get(j).website + "," + list.get(k).website;// O(website.length)
                        sequenceMap.putIfAbsent(pattern, new HashSet<>());
                        sequenceMap.get(pattern).add(user);
                    }
                }
            }
        }
        String res = "";
        int cnt = 0;
        for (String s : sequenceMap.keySet()) {
            int size = sequenceMap.get(s).size();
            if (size > cnt) {
                cnt = size;
                res = s;
                continue;
            }
            if (size == cnt) {
                if (s.compareTo(res) < 0) {
                    res = s;
                }
            }
        }
        return Arrays.asList(res.split(","));
    }
    
    class Pair {
        String website;
        int timestamp;
        public Pair(String website, int timestamp) {
            this.website = website;
            this.timestamp = timestamp;
        }
    }
}
