import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;

public class P7562 {

    /**
    * [문제]
    * 체스판 위에 한 나이트가 놓여져 있다.
    *  나이트가 한 번에 이동할 수 있는 칸은 아래 그림에 나와있다.
    *  나이트가 이동하려고 하는 칸이 주어진다.
    *  나이트는 몇 번 움직이면 이 칸으로 이동할 수 있을까?

    * 
    * [입력]
    * 입력의 첫째 줄에는 테스트 케이스의 개수가 주어진다.
    * 각 테스트 케이스는 세 줄로 이루어져 있다.
    *  첫째 줄에는 체스판의 한 변의 길이 l(4 ≤ l ≤ 300)이 주어진다.
    *  체스판의 크기는 l × l이다.
    *  체스판의 각 칸은 두 수의 쌍 {0, .
    * .
    * .
    * , l-1} × {0, .
    * .
    * .
    * , l-1}로 나타낼 수 있다.
    *  둘째 줄과 셋째 줄에는 나이트가 현재 있는 칸, 나이트가 이동하려고 하는 칸이 주어진다.
    * 

    * 
    * [출력]
    * 각 테스트 케이스마다 나이트가 최소 몇 번만에 이동할 수 있는지 출력한다.
    * 

    */
    static final int[][] DIR = {
            {2, 1}, {2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}, {-2, 1}, {-2, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(reader.readLine());
        StringJoiner joiner = new StringJoiner("\n");
        while (T-- > 0) {
            int L = Integer.parseInt(reader.readLine());
            int[] start = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] end = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            boolean[][] isVisited = new boolean[L][L];
            Queue<int[]> q = new LinkedList<>();
            q.offer(new int[]{start[0], start[1], 0});

            while (!q.isEmpty()) {
                int[] current = q.poll();
                int x = current[0];
                int y = current[1];
                if (x == end[0] && y == end[1]) {
                    joiner.add(String.valueOf(current[2]));
                    break;
                }

                for (int[] dir : DIR) {
                    int nx = x + dir[0];
                    int ny = y + dir[1];
                    if (0<= nx && nx < L && 0 <= ny && ny < L && !isVisited[nx][ny]) {
                        q.offer(new int[]{nx, ny, current[2] + 1});
                        isVisited[nx][ny] = true;
                    }
                }
            }
        }

        System.out.println(joiner);
    }
}