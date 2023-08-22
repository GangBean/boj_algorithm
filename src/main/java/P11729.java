import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class P11729 {

    /**
    * [문제]
    * 세 개의 장대가 있고 첫 번째 장대에는 반경이 서로 다른 n개의 원판이 쌓여 있다.
    *  각 원판은 반경이 큰 순서대로 쌓여있다.
    *  이제 수도승들이 다음 규칙에 따라 첫 번째 장대에서 세 번째 장대로 옮기려 한다.
    * 이 작업을 수행하는데 필요한 이동 순서를 출력하는 프로그램을 작성하라.
    *  단, 이동 횟수는 최소가 되어야 한다.
    * 아래 그림은 원판이 5개인 경우의 예시이다.
    * 

    * 
    * [입력]
    * 첫째 줄에 첫 번째 장대에 쌓인 원판의 개수 N (1 ≤ N ≤ 20)이 주어진다.
    * 

    * 
    * [출력]
    * 첫째 줄에 옮긴 횟수 K를 출력한다.
    * 두 번째 줄부터 수행 과정을 출력한다.
    *  두 번째 줄부터 K개의 줄에 걸쳐 두 정수 A B를 빈칸을 사이에 두고 출력하는데, 이는 A번째 탑의 가장 위에 있는 원판을 B번째 탑의 가장 위로 옮긴다는 뜻이다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = reader.readLine();
        int N = Integer.parseInt(s);
        List<Node> ret = new ArrayList<>();
        findRoute(ret, N, 1, 3);
        System.out.println(ret.size());
        StringJoiner stringJoiner = new StringJoiner("\n");
        ret.stream().map(Node::toString).forEach(stringJoiner::add);
        System.out.print(stringJoiner);
    }

    private static void findRoute(List<Node> route, int N, int from, int to) {
        if (N == 1) {
            route.add(new Node(from, to));
            return;
        }
        findRoute(route, N - 1, from, 6 - (from + to));
        findRoute(route, 1, from, to);
        findRoute(route, N - 1, 6 - (from + to), to);
    }

    static class Node {
        int from;
        int to;

        public Node(int from, int to) {
            this.from = from;
            this.to = to;
        }

        @Override
        public String toString() {
            return from + " " + to;
        }
    }
}