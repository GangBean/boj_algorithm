package programmers;

public class L42898 {
    static class Solution {
        static final int[][] DIR = {
                {0, -1}, {-1, 0}
        };
        static final int DIVISOR = 1_000_000_007;
        public int solution(int m, int n, int[][] puddles) {
            int[][] dp = new int[m][n];
            for (int[] puddle : puddles) {
                dp[puddle[0]-1][puddle[1]-1] = -1;
            }
            dp[0][0] = 1;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n ; j++) {
                    if (dp[i][j] == -1) continue;
                    for (int[] dir : DIR) {
                        int ni = i + dir[0];
                        int nj = j + dir[1];
                        if (0 <= ni && ni < m
                                && 0 <= nj && nj < n
                                && dp[ni][nj] >= 0) {
                            dp[i][j] += (dp[ni][nj] % DIVISOR);
                            dp[i][j] %= DIVISOR;
                        }
                    }
                }
            }
            return dp[m-1][n-1];
        }
    }
}
