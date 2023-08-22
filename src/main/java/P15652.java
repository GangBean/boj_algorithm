import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class P15652 {

    /**
    * [문제]
    * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인 수열을 모두 구하는 프로그램을 작성하시오.
    * 

    * 
    * [입력]
    * 첫째 줄에 자연수 N과 M이 주어진다.
    *  (1 ≤ M ≤ N ≤ 8)

    * 
    * [출력]
    * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력한다.
    *  중복되는 수열을 여러 번 출력하면 안되며, 각 수열은 공백으로 구분해서 출력해야 한다.
    * 수열은 사전 순으로 증가하는 순서로 출력해야 한다.
    * 

    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int[] input = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        StringJoiner stringJoiner = new StringJoiner("\n");
        findDuplicateCombination(stringJoiner, new ArrayList<>(), input[0], input[1], 1);
        System.out.println(stringJoiner);
    }

    private static void findDuplicateCombination(StringJoiner stringJoiner, List<Integer> list, int N, int M, int prev) {
        if (list.size() == M) {
            StringJoiner joiner = new StringJoiner(" ");
            list.stream().map(String::valueOf).forEach(joiner::add);
            stringJoiner.add(joiner.toString());
            return;
        }

        for (int i = prev; i <= N; i++) {
            list.add(i);
            findDuplicateCombination(stringJoiner, list, N, M, i);
            list.remove(list.size() - 1);
        }
    }
}