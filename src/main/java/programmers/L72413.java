package programmers;

import java.util.Arrays;

public class L72413 {

    // 두 지점간의 최소 요금 게산
    // 현재 위치에서 도착지점이 아니고 도달한적 없는 위치로 같이 이동하는 경우, 각자 도착지점으로 이동하는 경우를 계산
    // 도착했을 경우 예상비용 업데이트
    static class Solution {
        static final int INF = 200 * 100_000 + 1;
        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = 0;
            // 100_000 * 200 < 2^31
            int[][] minFares = new int[n+1][n+1];
            for (int i = 1; i<=n; i++) {
                Arrays.fill(minFares[i], INF);
            }
            for (int[] fare : fares) {
                minFares[fare[0]][fare[1]] = fare[2];
                minFares[fare[1]][fare[0]] = fare[2];
            }

            for (int i = 1; i <= n; i++) {
                minFares[i][i] = 0;
            }

            for (int k = 1; k <= n; k++) {
                for (int i = 1; i <= n; i++) {
                    for (int j = 1; j <= n; j++) {
                        if (minFares[i][k] + minFares[k][j] < minFares[i][j]) {
                            minFares[i][j] = minFares[i][k] + minFares[k][j];
                            minFares[j][i] = minFares[i][j];
                        }
                    }
                }
            }


            // for (int i = 1; i <= n; i++) {
            //     for (int j = 1; j <= n; j++) {
            //         if (minFares[i][j] == INF) {
            //             System.out.print("INF\t");
            //         } else {
            //             System.out.print(minFares[i][j] + "\t");
            //         }
            //     }
            //     System.out.println();
            // }

            answer = minFares[s][a] + minFares[s][b];

            for (int i = 1; i <= n; i++) {
                if (i == s) continue;
                answer = Math.min(answer, minFares[s][i] + minFares[i][a] + minFares[i][b]);
            }
            return answer;
        }
    }
}
