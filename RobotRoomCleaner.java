import java.util.HashSet;
import java.util.Set;

interface Robot {
    // Returns true if the cell in front is open and robot moves into the cell.
    // Returns false if the cell in front is blocked and robot stays in the current cell.
    public boolean move();
    
    // Robot will stay in the same cell after calling turnLeft/turnRight.
    // Each turn will be 90 degrees.
    public void turnLeft();
    public void turnRight();
    
    // Clean the current cell and mark the cell as 2.
    public void clean();
}

public class RobotRoomCleaner {
    // dfs backtracking + mark visited - time: O(m * n) space: O(m * n)
    public void cleanRoom(Robot robot) {
        Set<String> set = new HashSet<>();
        dfs(robot, 0, 0, 0, set);// robot, i, j, dir, set
    }

    private static final int[][] DIRS = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};// up, left, down, right

    private void dfs(Robot robot, int i, int j, int dir, Set<String> set) {
        set.add(i + "," + j);
        robot.clean();
        for (int k = 0; k < 4; k++) {
            int x = i + DIRS[dir][0], y = j + DIRS[dir][1];
            if (!set.contains(x + "," + y) && robot.move()) {
                dfs(robot, x, y, dir, set);
                goBack(robot);
            }
            robot.turnLeft();
            dir = (dir + 1) % 4;
        }
    }

    private void goBack(Robot robot) {
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
    }
}
