import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P7576 {

    /**
    * [문제]
    * 철수의 토마토 농장에서는 토마토를 보관하는 큰 창고를 가지고 있다.
    *  토마토는 아래의 그림과 같이 격자 모양 상자의 칸에 하나씩 넣어서 창고에 보관한다.
    * 창고에 보관되는 토마토들 중에는 잘 익은 것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다.
    *  보관 후 하루가 지나면, 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
    *  하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 방향에 있는 토마토를 의미한다.
    *  대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우는 없다고 가정한다.
    *  철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게 되는지, 그 최소 일수를 알고 싶어 한다.
    * 토마토를 창고에 보관하는 격자모양의 상자들의 크기와 익은 토마토들과 익지 않은 토마토들의 정보가 주어졌을 때, 며칠이 지나면 토마토들이 모두 익는지, 그 최소 일수를 구하는 프로그램을 작성하라.
    *  단, 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
    * 

    * 
    * [입력]
    * 첫 줄에는 상자의 크기를 나타내는 두 정수 M,N이 주어진다.
    *  M은 상자의 가로 칸의 수, N은 상자의 세로 칸의 수를 나타낸다.
    *  단, 2 ≤ M,N ≤ 1,000 이다.
    *  둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다.
    *  즉, 둘째 줄부터 N개의 줄에는 상자에 담긴 토마토의 정보가 주어진다.
    *  하나의 줄에는 상자 가로줄에 들어있는 토마토의 상태가 M개의 정수로 주어진다.
    *  정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸을 나타낸다.
    * 토마토가 하나 이상 있는 경우만 입력으로 주어진다.
    * 

    * 
    * [출력]
    * 여러분은 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다.
    *  만약, 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력해야 하고, 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야 한다.
    * 

    */
    static final int[][] DIR = {{1,0}, {0,1}, {-1,0}, {0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int[] input = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[1];
        int M = input[0];
        int[][] map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    q.offer(new int[]{i, j, 0});
                }
            }
        }

        int time = 0;
        while (!q.isEmpty()) {
            int[] current = q.poll();
            time = current[2];

            for (int dir = 0; dir < 4; dir++) {
                int nx = current[0] + DIR[dir][0];
                int ny = current[1] + DIR[dir][1];
                if (0 <= nx && nx < N && 0 <= ny && ny < M && map[nx][ny] == 0) {
                    q.offer(new int[]{nx, ny, current[2] + 1});
                    map[nx][ny] = 1;
                }
            }
        }

        System.out.println(checkBox(map, time));
    }

    private static int checkBox(int[][] map, int time) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0 ; j < map[0].length ; j++) {
                if (map[i][j] == 0) {
                    return -1;
                }
            }
        }
        return time;
    }
}