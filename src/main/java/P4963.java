import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringJoiner;

public class P4963 {

    /**
    * [문제]
    * 정사각형으로 이루어져 있는 섬과 바다 지도가 주어진다.
    *  섬의 개수를 세는 프로그램을 작성하시오.
    * 한 정사각형과 가로, 세로 또는 대각선으로 연결되어 있는 사각형은 걸어갈 수 있는 사각형이다.
    * 두 정사각형이 같은 섬에 있으려면, 한 정사각형에서 다른 정사각형으로 걸어서 갈 수 있는 경로가 있어야 한다.
    *  지도는 바다로 둘러싸여 있으며, 지도 밖으로 나갈 수 없다.
    *

    *
    * [입력]
    * 입력은 여러 개의 테스트 케이스로 이루어져 있다.
    *  각 테스트 케이스의 첫째 줄에는 지도의 너비 w와 높이 h가 주어진다.
    *  w와 h는 50보다 작거나 같은 양의 정수이다.
    * 둘째 줄부터 h개 줄에는 지도가 주어진다.
    *  1은 땅, 0은 바다이다.
    * 입력의 마지막 줄에는 0이 두 개 주어진다.
    *

    *
    * [출력]
    * 각 테스트 케이스에 대해서, 섬의 개수를 출력한다.
    *

    */
    static final int[][] DIR = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}
    };
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringJoiner joiner = new StringJoiner("\n");
        while (true) {
            int[] input = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            if (input[0] == 0 && input[1] == 0) break;
            int[][] map = new int[input[1]][input[0]];
            for (int i = 0; i < input[1]; i++) {
                map[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            Queue<int[]> q = new LinkedList<>();
            int color = 2;
            for (int y = 0; y < input[1]; y++) {
                for (int x = 0; x < input[0]; x++) {
                    if (map[y][x] == 1) {
                        q.offer(new int[]{x, y});
                        map[y][x] = color;

                        while (!q.isEmpty()) {
                            int[] current = q.poll();
                            int cx = current[0];
                            int cy = current[1];

                            for (int[] dir : DIR) {
                                int nx = cx + dir[0];
                                int ny = cy + dir[1];
                                if (0 <= nx && nx < input[0] && 0 <= ny && ny < input[1] && map[ny][nx] == 1) {
                                    map[ny][nx] = color;
                                    q.offer(new int[]{nx, ny});
                                }
                            }
                        }
                        color++;
                    }
                }
            }
            joiner.add(String.valueOf(color - 2));
        }

        System.out.println(joiner);
    }
}