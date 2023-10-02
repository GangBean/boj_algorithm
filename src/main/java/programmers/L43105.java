package programmers;

import java.util.Arrays;

public class L43105 {
    // dp[x][y] = max(dp[x-1][y-1], dp[x-1][y]) + a[x][y]
    static int[][] dp;
    public int solution(int[][] triangle) {
        int N = triangle.length;

        dp = new int[N][];
        for (int i = 0; i < N; i++) {
            dp[i] = Arrays.copyOf(triangle[i], triangle[i].length);
        }
        for (int x = 1; x < N; x++) {
            for (int y = 0; y < dp[x].length; y++) {
                if (y == 0) {
                    dp[x][y] += dp[x-1][y];
                    continue;
                }
                if (y == dp[x].length - 1) {
                    dp[x][y] += dp[x-1][y-1];
                    continue;
                }
                dp[x][y] += Math.max(dp[x-1][y-1], dp[x-1][y]);
            }
        }

        return Arrays.stream(dp[N-1]).max().getAsInt();
    }
}
