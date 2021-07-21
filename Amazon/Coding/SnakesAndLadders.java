package Amazon.Coding;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class SnakesAndLadders {
    // bfs - time: O(n) space: O(n)
    // if val != -1, then should add val to q instead of next
    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        Queue<Integer> q = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();
        q.offer(1);
        set.add(1);
        int move = 0;
        while (q.size() > 0) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int cur = q.poll();
                if (cur == n * n) return move;// check out at q.poll()
                for (int j = 1; j <= 6 && cur + j <= n * n; j++) {
                    int next = cur + j;
                    int val = getValue(board, next);
                    if (val != -1) {
                        if (set.add(val)) {
                            q.offer(val);
                        }
                    } else {
                        if (set.add(next)) {
                            q.offer(next);
                        }
                    }
                }
            }
            move++;
        }
        return -1;
    }
    
    private int getValue(int[][] board, int i) {
        int n = board.length;
        int row = (i - 1) / n;
        int col = (i - 1) % n;
        if (row % 2 == 1) {
            col = n - 1 - col;
        }
        return board[n - 1 - row][col];
    }
}
