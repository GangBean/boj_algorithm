package programmers;

import java.util.LinkedList;
import java.util.Queue;

public class L67259 {
    static class Solution {
        static final int[][] DIR = {
            {1, 0}, {0, 1}, {-1, 0}, {0, -1} // 아래, 오른쪽, 위, 왼쪽
        };
        static final int MAX_VALUE = 1_000_000_000;
        public int solution(int[][] board) {
            int answer = 0;
            int[][][] dp = new int[board.length][board[0].length][4]; // x, y 위치에 d 방향으로 연결된 길을 만들때 필요한 최소 비용
            int N = board.length;
            for (int x = 0; x < dp.length; x++) {
                for (int y = 0; y <dp[0].length; y++) {
                    for (int dir = 0; dir < dp[0][0].length; dir++) {
                        dp[x][y][dir] = MAX_VALUE;
                    }
                }
            }
            // System.out.print(MAX_VALUE);
            Queue<int[]> q = new LinkedList<>();
            for (int dir = 0; dir < dp[0][0].length; dir++) {
                dp[0][0][dir] = 0;
                int nx = DIR[dir][0];
                int ny = DIR[dir][1];
                if (0 <= nx && nx < N
                    && 0 <= ny && ny < N
                    && board[nx][ny] == 0) {
                    q.offer(new int[]{nx, ny, 100, dir}); // x, y, 비용, 방향
                }
            }

            while (!q.isEmpty()) {
                int[] current = q.poll();
                int x = current[0];
                int y = current[1];
                int cost = current[2];
                int prevDir = current[3];
                for (int d = 0; d < DIR.length; d++) {
                    int[] dir = DIR[d];
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    int nextCost = cost + ((prevDir + d) % 2 == 0 ? 100 : 600); // 다음 비용
                    if (0 <= nx && nx < N
                        && 0 <= ny && ny < N
                        && board[nx][ny] == 0
                        && dp[nx][ny][d] > nextCost) {
                        dp[nx][ny][d] = nextCost;
                        q.offer(new int[]{nx, ny, dp[nx][ny][d], d});
                    }
                }
            }

            return Math.min(
                Math.min(dp[N-1][N-1][0], dp[N-1][N-1][1]),
                Math.min(dp[N-1][N-1][2], dp[N-1][N-1][3])
            );
        }
    }
}
