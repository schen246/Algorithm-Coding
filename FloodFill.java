public class FloodFill {
    private static final int[][] DIRS = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (newColor == image[sr][sc]) {
            return image;
        }
        dfs(image, sr, sc, newColor, image[sr][sc]);
        return image;
    }
    
    private void dfs(int[][] image, int r, int c, int newColor, int target) {
        image[r][c] = newColor;
        for (int[] dir : DIRS) {
            int x = r + dir[0], y = c + dir[1];
            if (x >= 0 && x < image.length && y >= 0 && y < image[0].length && image[x][y] == target) {
                dfs(image, x, y, newColor, target);
            }
        }
    }
    // time: O(m * n) space: O(m * n)
}
