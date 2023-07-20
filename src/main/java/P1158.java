import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class P1158 {

    /**
    * [문제]
    * 요세푸스 문제는 다음과 같다.
    * 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다.
    *  이제 순서대로 K번째 사람을 제거한다.
    *  한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다.
    *  이 과정은 N명의 사람이 모두 제거될 때까지 계속된다.
    *  원에서 사람들이 제거되는 순서를 (N, K)-요세푸스 순열이라고 한다.
    *  예를 들어 (7, 3)-요세푸스 순열은 <3, 6, 2, 7, 5, 1, 4>이다.
    * N과 K가 주어지면 (N, K)-요세푸스 순열을 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다.
    *  (1 ≤ K ≤ N ≤ 5,000)

    * 
    * [출력]
    * 예제와 같이 요세푸스 순열을 출력한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] split = reader.readLine().split(" ");
        int N = Integer.parseInt(split[0]);
        int K = Integer.parseInt(split[1]);

        Queue<Integer> q = new LinkedList<>(
                IntStream.range(1, N + 1).boxed().collect(Collectors.toList()));
        List<Integer> result = new ArrayList<>();
        while (!q.isEmpty()) {
            int cnt = K;
            while (cnt-- > 1) {
                q.offer(q.poll());
            }
            result.add(q.poll());
        }
        StringJoiner stringJoiner = new StringJoiner(", ", "<", ">");
        result.stream().map(String::valueOf).forEach(stringJoiner::add);
        System.out.println(stringJoiner);
    }
}