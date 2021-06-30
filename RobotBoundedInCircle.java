public class RobotBoundedInCircle {
    // symmetric - time: O(n) space: O(1)
    private static final int[][] DIRS = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
    
    public boolean isRobotBounded(String instructions) {
        int x = 0, y = 0, dir = 0;
        for (int i = 0; i < 4; i++) {
            for (char ch : instructions.toCharArray()) {
                if (ch == 'L') {
                    dir = (dir + 1) % 4;
                } else if (ch == 'R') {
                    dir = (dir + 3) % 4;
                } else {
                    x += DIRS[dir][0];
                    y += DIRS[dir][1];
                }
            }
        }
        return x == 0 && y == 0 && dir == 0;
    }
}
