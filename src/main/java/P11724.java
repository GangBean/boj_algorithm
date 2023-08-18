import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;

public class P11724 {

    /**
    * [문제]
    * 방향 없는 그래프가 주어졌을 때, 연결 요소 (Connected Component)의 개수를 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다.
    *  (1 ≤ N ≤ 1,000, 0 ≤ M ≤ N×(N-1)/2) 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다.
    *  (1 ≤ u, v ≤ N, u ≠ v) 같은 간선은 한 번만 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에 연결 요소의 개수를 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int[] input = Arrays.stream(s.split(" ")).mapToInt(Integer::parseInt).toArray();
        int N = input[0];
        int M = input[1];

        int count = 0;
        boolean[] isChecked = new boolean[N];
        boolean[][] isConnected = new boolean[N][N];

        while (M-- > 0) {
            int[] vertex = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

            isConnected[vertex[0]-1][vertex[1]-1] = true;
            isConnected[vertex[1]-1][vertex[0]-1] = true;
        }

        for (int i = 0; i < N; i++) {
            if (isChecked[i]) {
                continue;
            }
            findConnectedComponent(isConnected, isChecked, i);
            count++;
        }

        System.out.println(count);
    }

    private static void findConnectedComponent(boolean[][] isConnected, boolean[] isChecked, int idx) {
        Stack<Integer> s = new Stack<>();
        s.push(idx);
        isChecked[idx] = true;

        while (!s.isEmpty()) {
            int current = s.pop();
            for (int next = 0; next < isConnected[current].length; next++) {
                if (!isConnected[current][next] || isChecked[next]) {
                    continue;
                }
                s.push(next);
                isChecked[next] = true;
            }
        }
    }
}