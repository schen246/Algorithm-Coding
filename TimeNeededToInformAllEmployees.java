import java.util.HashMap;

public class TimeNeededToInformAllEmployees {
    public int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        buildGraph(manager, map);
        int[] res = new int[1];
        dfs(headID, 0, map, informTime, res);
        return res[0];
    }

    private void dfs(int id, int minutes, Map<Integer, List<Integer>> map, int[] informTime, int[] res) {
        if (map.get(id) == null) {
            res[0] = Math.max(res[0], minutes);
            return;
        }
        for (int nei : map.get(id)) {
            dfs(nei, minutes + informTime[id], map, informTime, res);
        }
    }

    private void buildGraph(int[] manager, Map<Integer, List<Integer>> map) {
        for (int i = 0; i < manager.length; i++) {
            if (manager[i] == -1) {
                continue;
            }
            map.putIfAbsent(manager[i], new ArrayList<>());
            map.get(manager[i]).add(i);
        }
    }
    // time: O(n) space: O(n)
}
