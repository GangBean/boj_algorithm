package programmers;

public class L1832 {
    static class Solution {
        static final int MOD = 20170805;
        static final int[][] DIR = {
                {0,-1}, {-1,0} // 위쪽, 왼쪽
        };
        public int solution(int m, int n, int[][] cityMap) {
            int answer = 0;
            int[][][] dp = new int[m][n][2]; // x, y, 이전방향
            // dp[x][y][0] : (x,y) 에 위쪽으로 도착
            // dp[x][y][1] : (x,y) 에 왼쪽으로 도착
            dp[0][0][0] = 1;
            dp[0][0][1] = 0;
            for (int x = 0; x < m; x++) {
                for (int y = 0; y < n; y++) {
                    if (cityMap[x][y] == 1) continue; // 통행불가
                    for (int d = 0; d < DIR.length; d++) {
                        int[] dir = DIR[d];
                        int px = x + dir[0];
                        int py = y + dir[1];
                        if (0 <= px && px < m
                                && 0 <= py && py < n) {
                            if (cityMap[px][py] == 2) {
                                dp[x][y][d] += dp[px][py][d];
                                dp[x][y][d] %= MOD;
                                continue;
                            }
                            dp[x][y][d] += dp[px][py][0];
                            dp[x][y][d] += dp[px][py][1];
                            dp[x][y][d] %= MOD;
                        }
                    }
                }
            }
            return (dp[m-1][n-1][0] + dp[m-1][n-1][1]) % MOD;
        }
    }
}
