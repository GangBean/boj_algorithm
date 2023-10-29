package programmers;

import java.util.Arrays;

public class L118668 {

    static class Solution {
        private static final int INF = 300;
        public int solution(int alp, int cop, int[][] problems) {
            int maxAlp = alp;
            int maxCop = cop;
            for (int[] problem : problems) {
                maxAlp = Math.max(maxAlp, problem[0]);
                maxCop = Math.max(maxCop, problem[1]);
            }
            int[][] dp = new int[maxAlp+1][maxCop+1];
            for (int[] arr : dp) {
                Arrays.fill(arr, INF);
            }
            dp[alp][cop] = 0;

            for (int a = alp; a <= maxAlp; a++) {
                for (int c = cop; c <= maxCop; c++) {
                    dp[Math.min(a+1, maxAlp)][c] = Math.min(dp[Math.min(a+1, maxAlp)][c], dp[a][c]+1);
                    dp[a][Math.min(c+1, maxCop)] = Math.min(dp[a][Math.min(c+1, maxCop)], dp[a][c]+1);
                    for (int[] problem : problems) {
                        if (a >= problem[0] && c >= problem[1]) {
                            int na = Math.min(a + problem[2], maxAlp);
                            int nc = Math.min(c + problem[3], maxCop);
                            dp[na][nc] = Math.min(dp[na][nc], dp[a][c] + problem[4]);
                        }
                    }
                }
            }
            return dp[maxAlp][maxCop];
        }
    }

}
