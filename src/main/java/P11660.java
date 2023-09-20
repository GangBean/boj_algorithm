import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;

public class P11660 {

    /**
    * [문제]
    * N×N개의 수가 N×N 크기의 표에 채워져 있다.
    *  (x1, y1)부터 (x2, y2)까지 합을 구하는 프로그램을 작성하시오.
    *  (x, y)는 x행 y열을 의미한다.
    * 예를 들어, N = 4이고, 표가 아래와 같이 채워져 있는 경우를 살펴보자.
    * 여기서 (2, 2)부터 (3, 4)까지 합을 구하면 3+4+5+4+5+6 = 27이고, (4, 4)부터 (4, 4)까지 합을 구하면 7이다.
    * 표에 채워져 있는 수와 합을 구하는 연산이 주어졌을 때, 이를 처리하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 표의 크기 N과 합을 구해야 하는 횟수 M이 주어진다.
    *  (1 ≤ N ≤ 1024, 1 ≤ M ≤ 100,000) 둘째 줄부터 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로 주어진다.
    *  다음 M개의 줄에는 네 개의 정수 x1, y1, x2, y2 가 주어지며, (x1, y1)부터 (x2, y2)의 합을 구해 출력해야 한다.
    *  표에 채워져 있는 수는 1,000보다 작거나 같은 자연수이다.
    *  (x1 ≤ x2, y1 ≤ y2)

    * 
    * [출력]
    * 총 M줄에 걸쳐 (x1, y1)부터 (x2, y2)까지 합을 구해 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] s = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = s[0];
        int M = s[1];
        int[][] map = new int[N+1][N+1];
        for (int i = 0; i < N; i++) {
            int[] s1 = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            for (int j = 0; j < N; j++) {
                map[i+1][j+1] = s1[j];
            }
        }

        int[][] dp = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = - dp[i-1][j-1] + dp[i-1][j] + dp[i][j-1] + map[i][j];
            }
        }

        StringJoiner stringJoiner = new StringJoiner("\n");
        for (int t = 0; t < M; t++) {
            int[] inputs = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int x1 = inputs[0];
            int y1 = inputs[1];
            int x2 = inputs[2];
            int y2 = inputs[3];
            stringJoiner.add(String.valueOf(
                    dp[x1-1][y1-1] + dp[x2][y2] - dp[x2][y1-1] - dp[x1-1][y2]
            ));
        }
        System.out.println(stringJoiner);
    }
}