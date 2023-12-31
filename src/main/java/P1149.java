import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1149 {

    /**
    * [문제]
    * RGB거리에는 집이 N개 있다.
    *  거리는 선분으로 나타낼 수 있고, 1번 집부터 N번 집이 순서대로 있다.
    * 집은 빨강, 초록, 파랑 중 하나의 색으로 칠해야 한다.
    *  각각의 집을 빨강, 초록, 파랑으로 칠하는 비용이 주어졌을 때, 아래 규칙을 만족하면서 모든 집을 칠하는 비용의 최솟값을 구해보자.
    * 

    * 
    * [입력]
    * 첫째 줄에 집의 수 N(2 ≤ N ≤ 1,000)이 주어진다.
    *  둘째 줄부터 N개의 줄에는 각 집을 빨강, 초록, 파랑으로 칠하는 비용이 1번 집부터 한 줄에 하나씩 주어진다.
    *  집을 칠하는 비용은 1,000보다 작거나 같은 자연수이다.
    * 

    * 
    * [출력]
    * 첫째 줄에 모든 집을 칠하는 비용의 최솟값을 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int N = Integer.parseInt(s);
        int[][] ret = new int[N][3];
        for (int i = 0; i < N; i++) {
            int[] costs = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
            for (int color = 0; color < 3; color++) {
                if (i == 0) {
                    ret[i][color] = costs[color];
                    continue;
                }
                ret[i][color] = costs[color] + Math.min(ret[i-1][(color+1)%3], ret[i-1][(color+2)%3]);
            }
        }

        System.out.println(Arrays.stream(ret[N-1]).min().orElseThrow());
    }
}