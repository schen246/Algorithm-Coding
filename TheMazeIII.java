public class TheMazeIII {
    // bfs - time: O(mnlogmn) space: O(mn * mn)
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        int m = maze.length, n = maze[0].length;
        if (ball[0] == hole[0] && ball[1] == hole[1]) {
            return "";
        }
        PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
            if (a.dist == b.dist) {
                return a.path.compareTo(b.path);// small letter first
            }
            return a.dist - b.dist;// small dist first
        });
        Set<Integer> set = new HashSet<>();
        pq.offer(new Item(ball[0], ball[1], 0, ""));
        while (pq.size() > 0) {
            Item cur = pq.poll();
            if (cur.x == hole[0] && cur.y == hole[1]) {
                return cur.path;
            }
            if (!set.add(cur.x * n + cur.y)) {
                continue;
            }
            for (Item nei : getNei(maze, cur, hole)) {
                if (!set.contains(nei.x * n + nei.y)) {
                    pq.offer(nei);
                }
            }
        }
        return "impossible";
    }
    
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private static final char[] CHARS = new char[]{'u', 'd', 'l', 'r'};
    
    private List<Item> getNei(int[][] maze, Item cur, int[] hole) {
        List<Item> res = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            int[] dir = DIRS[i];
            int x = cur.x + dir[0], y = cur.y + dir[1], dist = cur.dist + 1;
            while (x >= 0 && x < maze.length && y >= 0 && y < maze[0].length && maze[x][y] != 1) {
                if (x == hole[0] && y == hole[1]) {
                    res.add(new Item(x, y, dist, cur.path + CHARS[i]));
                    break;
                }
                x += dir[0];
                y += dir[1];
                dist++;
            }
            if (x - dir[0] == cur.x && y - dir[1] == cur.y) {
                continue;
            }
            res.add(new Item(x - dir[0], y - dir[1], dist - 1, cur.path + CHARS[i]));
        }
        return res;
    }
}

class Item {
    int x;
    int y;
    int dist;
    String path;
    public Item(int x, int y, int dist, String path) {
        this.x = x;
        this.y = y;
        this.dist = dist;
        this.path = path;
    }
}