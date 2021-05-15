public class KeysAndRooms {
    public boolean canVisitAllRooms(int[][] rooms) {
        boolean[] visited = new boolean[rooms.length];
        dfs(rooms, 0, visited);
        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }
        return true;
    }

    private void dfs(int[][] rooms, int i, boolean[] visited) {
        if (visited[i]) {
            return;
        }
        visited[i] = true;
        for (int room : rooms[i]) {
            dfs(rooms, room, visited);
        }
    }
    // time: O(n) space: O(n)
}
