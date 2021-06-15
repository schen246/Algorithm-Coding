import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AccountsMerge {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        // find connected components
        // step 1: build graph
        // step 2: dfs
        Map<String, List<String>> map = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        buildGraph(map, emailToName, accounts);
        List<List<String>> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for (String email : emailToName.keySet()) {
            if (!visited.contains(email)) {
                List<String> cur = new ArrayList<>();
                dfs(map, email, cur, visited);
                Collections.sort(cur);
                cur.add(0, emailToName.get(email));
                res.add(cur);
            }
        }
        return res;
    }

    private void dfs(Map<String, List<String>> map, String email, List<String> cur, Set<String> visited) {
        if (visited.contains(email)) {
            return;
        }
        visited.add(email);
        cur.add(email);
        for (String nei : map.get(email)) {
            dfs(map, nei, cur, visited);
        }
    }

    private void buildGraph(Map<String, List<String>> map, Map<String, String> emailToMap, List<List<String>> accounts) {
        for (List<String> a : accounts) {
            String name = a.get(0);
            for (int i = 1; i < a.size(); i++) {
                String email = a.get(i);
                map.putIfAbsent(email, new ArrayList<>());
                emailToMap.put(email, name);
                if (i == 1) {
                    continue;
                }
                String pre = a.get(i - 1);
                map.get(pre).add(email);
                map.get(email).add(pre);
            }
        }
    }
    // time: O(n) + O(nodes) + O(nlogn) space: O(n)
}
