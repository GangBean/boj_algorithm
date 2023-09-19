import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class P11403 {

    /**
    * [문제]
    * 지민이는 N개의 원소를 포함하고 있는 양방향 순환 큐를 가지고 있다.
    *  지민이는 이 큐에서 몇 개의 원소를 뽑아내려고 한다.
    * 지민이는 이 큐에서 다음과 같은 3가지 연산을 수행할 수 있다.
    * 큐에 처음에 포함되어 있던 수 N이 주어진다.
    *  그리고 지민이가 뽑아내려고 하는 원소의 위치가 주어진다.
    *  (이 위치는 가장 처음 큐에서의 위치이다.
    * ) 이때, 그 원소를 주어진 순서대로 뽑아내는데 드는 2번, 3번 연산의 최솟값을 출력하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 큐의 크기 N과 뽑아내려고 하는 수의 개수 M이 주어진다.
    *  N은 50보다 작거나 같은 자연수이고, M은 N보다 작거나 같은 자연수이다.
    *  둘째 줄에는 지민이가 뽑아내려고 하는 수의 위치가 순서대로 주어진다.
    *  위치는 1보다 크거나 같고, N보다 작거나 같은 자연수이다.
    * 

    * 
    * [출력]
    * 첫째 줄에 문제의 정답을 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int[][] path = new int[N][];
        boolean[][] isVisit = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            path[i] = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (path[i][j] == 1) {
                    q.offer(new int[]{i, i, j});
                    isVisit[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            int[] current = q.poll();
            if (current[0] == current[2]) continue;
            for (int next = 0; next < N; next++) {
                if (path[current[2]][next] == 1 && !isVisit[current[0]][next]) {
                    q.offer(new int[]{current[0], current[2], next});
                    path[current[0]][current[2]] = 1;
                    path[current[0]][next] = 1;
                    isVisit[current[0]][current[2]] = true;
                    isVisit[current[0]][next] = true;
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(path[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
}