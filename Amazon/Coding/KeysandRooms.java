package Amazon.Coding;

import java.util.List;

public class KeysandRooms {
    // time: O(n) space: O(n)
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        boolean[] visited = new boolean[rooms.size()];
        dfs(rooms, 0, visited);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }
    
    private void dfs(List<List<Integer>> rooms, int index, boolean[] visited) {
        if (visited[index]) {
            return;
        }
        visited[index] = true;
        for (int nei : rooms.get(index)) {
            dfs(rooms, nei, visited);
        }
    }
}
