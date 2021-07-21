package Amazon.Coding;
public class RobotBoundedInCircle {
    private static final int[][] DIRS = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

    public boolean isRobotBounded(String instructions) {
        int dir = 0;
        int x = 0, y = 0;
        for (int i = 0; i < 4; i++) {
            for (char c : instructions.toCharArray()) {
                if (c == 'L') {
                    dir = dir == 3 ? 0 : dir + 1;
                } else if (c == 'R') {
                    dir = dir == 0 ? 3 : dir - 1;
                } else {
                    x += DIRS[dir][0];
                    y += DIRS[dir][1];
                }
            }
        }
        return x == 0 && y == 0 && dir == 0;
    }
    // time: O(4 * n) space: O(1)
}
