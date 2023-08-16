import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class P2178 {

    /**
    * [문제]
    * N×M크기의 배열로 표현되는 미로가 있다.
    * 미로에서 1은 이동할 수 있는 칸을 나타내고, 0은 이동할 수 없는 칸을 나타낸다.
    *  이러한 미로가 주어졌을 때, (1, 1)에서 출발하여 (N, M)의 위치로 이동할 때 지나야 하는 최소의 칸 수를 구하는 프로그램을 작성하시오.
    *  한 칸에서 다른 칸으로 이동할 때, 서로 인접한 칸으로만 이동할 수 있다.
    * 위의 예에서는 15칸을 지나야 (N, M)의 위치로 이동할 수 있다.
    *  칸을 셀 때에는 시작 위치와 도착 위치도 포함한다.
    * 

    * 
    * [입력]
    * 첫째 줄에 두 정수 N, M(2 ≤ N, M ≤ 100)이 주어진다.
    *  다음 N개의 줄에는 M개의 정수로 미로가 주어진다.
    *  각각의 수들은 붙어서 입력으로 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에 지나야 하는 최소의 칸 수를 출력한다.
    *  항상 도착위치로 이동할 수 있는 경우만 입력으로 주어진다.
    * 

    */

    static final int[] dx = {0, 1, 0, -1};
    static final int[] dy = {1, 0, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String[] ss = s.split(" ");
        int N = Integer.parseInt(ss[0]);
        int K = Integer.parseInt(ss[1]);
        char[][] map = new char[N][K];
        for (int i = 0; i < N; i++) {
            map[i] = reader.readLine().toCharArray();
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0, 1});
        map[0][0] = '0';

        while (!q.isEmpty()) {
            int[] current = q.poll();
            int x = current[0];
            int y = current[1];
            if (x == N - 1 && y == K - 1) {
                System.out.println(current[2]);
                break;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                if (0 <= nx && nx < N && 0 <= ny && ny < K && map[nx][ny] == '1') {
                    q.offer(new int[]{nx, ny, current[2] + 1});
                    map[nx][ny] = '0';
                }
            }
        }
    }
}