import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1260 {

    /**
    * [문제]
    * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오.
    *  단, 방문할 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문할 수 있는 점이 없는 경우 종료한다.
    *  정점 번호는 1번부터 N번까지이다.
    * 

    * 
    * [입력]
    * 첫째 줄에 정점의 개수 N(1 ≤ N ≤ 1,000), 간선의 개수 M(1 ≤ M ≤ 10,000), 탐색을 시작할 정점의 번호 V가 주어진다.
    *  다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다.
    *  어떤 두 정점 사이에 여러 개의 간선이 있을 수 있다.
    *  입력으로 주어지는 간선은 양방향이다.
    * 

    * 
    * [출력]
    * 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다.
    *  V부터 방문된 점을 순서대로 출력하면 된다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        String[] ss = s.split(" ");
        int N = Integer.parseInt(ss[0]);
        int M = Integer.parseInt(ss[1]);
        int V = Integer.parseInt(ss[2]);

        boolean[][] connect = new boolean[N + 1][N + 1];
        while (M-- > 0) {
            String line = reader.readLine();
            String[] split = line.split(" ");
            int n1 = Integer.parseInt(split[0]);
            int n2 = Integer.parseInt(split[1]);
            connect[n1][n2] = true;
            connect[n2][n1] = true;
        }

        StringJoiner joiner = new StringJoiner(" ");
        dfs(connect, V).stream()
                .map(String::valueOf)
                .forEach(joiner::add);
        System.out.println(joiner);

        joiner = new StringJoiner(" ");
        bfs(connect, V).stream()
                .map(String::valueOf)
                .forEach(joiner::add);
        System.out.println(joiner);

        joiner = new StringJoiner(" ");
        dfs2(connect, V).stream()
                .map(String::valueOf)
                .forEach(joiner::add);
        System.out.println(joiner);
    }

    private static List<Integer> dfs(boolean[][] connect, int n) {
        boolean[] visited = new boolean[connect.length];
        Stack<Integer> s = new Stack<>();
        s.push(n);
        List<Integer> ret = new ArrayList<>();
        while (!s.isEmpty()) {
            int current = s.pop();
            if (!visited[current]) {
                ret.add(current);
                visited[current] = true;
            }
            for (int next = connect.length - 1; next >= 1; next--) {
                if (connect[current][next] && !visited[next]) {
                    s.push(next);
                }
            }
        }
        return ret;
    }

    private static List<Integer> dfs2(boolean[][] connect, int n) {
        boolean[] visited = new boolean[connect.length];
        Stack<Integer> s = new Stack<>();
        s.push(n);
        visited[n] = true;
        List<Integer> ret = new ArrayList<>();
        while (!s.isEmpty()) {
            int current = s.pop();
            ret.add(current);
            for (int next = 1; next < connect.length; next++) {
                if (connect[current][next] && !visited[next]) {
                    s.push(next);
                    visited[next] = true;
                    break;
                }
            }
        }
        return ret;
    }

    private static List<Integer> bfs(boolean[][] connect, int n) {
        boolean[] visited = new boolean[connect.length];
        Queue<Integer> q = new LinkedList<>();
        q.offer(n);
        visited[n] = true;
        List<Integer> ret = new ArrayList<>();
        while (!q.isEmpty()) {
            int current = q.poll();
            ret.add(current);
            for (int next = 1; next < connect.length; next++) {
                if (connect[current][next] && !visited[next]) {
                    q.offer(next);
                    visited[next] = true;
                }
            }
        }
        return ret;
    }
}