import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringJoiner;

public class P1753 {

    /**
    * [문제]
    * 방향그래프가 주어지면 주어진 시작점에서 다른 모든 정점으로의 최단 경로를 구하는 프로그램을 작성하시오.
    *  단, 모든 간선의 가중치는 10 이하의 자연수이다.
    * 

    * 
    * [입력]
    * 첫째 줄에 정점의 개수 V와 간선의 개수 E가 주어진다.
    *  (1 ≤ V ≤ 20,000, 1 ≤ E ≤ 300,000) 모든 정점에는 1부터 V까지 번호가 매겨져 있다고 가정한다.
    *  둘째 줄에는 시작 정점의 번호 K(1 ≤ K ≤ V)가 주어진다.
    *  셋째 줄부터 E개의 줄에 걸쳐 각 간선을 나타내는 세 개의 정수 (u, v, w)가 순서대로 주어진다.
    *  이는 u에서 v로 가는 가중치 w인 간선이 존재한다는 뜻이다.
    *  u와 v는 서로 다르며 w는 10 이하의 자연수이다.
    *  서로 다른 두 정점 사이에 여러 개의 간선이 존재할 수도 있음에 유의한다.
    * 

    * 
    * [출력]
    * 첫째 줄부터 V개의 줄에 걸쳐, i번째 줄에 i번 정점으로의 최단 경로의 경로값을 출력한다.
    *  시작점 자신은 0으로 출력하고, 경로가 존재하지 않는 경우에는 INF를 출력하면 된다.
    * 

    */
    static final int INF = 123_456_789;
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int start = Integer.parseInt(reader.readLine()) - 1;

        List<List<Node>> edges = new ArrayList<>();
        for (int i = 0; i < input[0]; i++) {
            edges.add(new ArrayList<>());
        }

        for (int i = 0; i < input[1]; i++) {
            int[] nums = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt)
                .toArray();
            edges.get(nums[0]-1).add(new Node(nums[1]-1, nums[2]));
        }

        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(start, 0));
        int[] dist = new int[input[0]];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        boolean[] isVisited = new boolean[input[0]];

        while (!q.isEmpty()) {
            Node current = q.poll();
            if (isVisited[current.end]) continue;
            isVisited[current.end] = true;

            for (Node next : edges.get(current.end)) {
                if (dist[current.end] + next.weight < dist[next.end]) {
                    dist[next.end] = dist[current.end] + next.weight;
                    q.offer(new Node(next.end, dist[next.end]));
                }
            }
        }

        StringJoiner ret = new StringJoiner("\n");
        Arrays.stream(dist).mapToObj(n -> (n == INF) ? "INF" : String.valueOf(n)).forEach(ret::add);
        System.out.println(ret);
    }

    static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(weight, o.weight);
        }
    }
}